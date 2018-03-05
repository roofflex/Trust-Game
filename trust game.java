import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Agent {
    private int coins;
    private String strategy;
    private boolean HasPutMoney;
    final Random random=new Random();
    Agent(int val1, String val2){
        coins=val1;
        strategy=val2;
    }
    public int getCoins(){
        return coins;
    }
    public String getStrategy() {
        return strategy;
    }
    public boolean getHasPutMoney(){
        return HasPutMoney;
    }
    public void setCoins(int Coins){
        this.coins=Coins;
    }
    public void setHasPutMoney(String Strategy){
        switch (Strategy){
            case "Naive":
                if (this.getCoins()>0){
                    this.HasPutMoney=true;
                    break;
                }
                else{
                    this.HasPutMoney=false;
                    break;
                }
            case "Greedy":
                this.HasPutMoney=false;
                break;
            case "Random":
                if (this.getCoins()>0) {
                    this.HasPutMoney = random.nextBoolean();
                }
                else this.HasPutMoney=false;
        }
    }
}

public class ex1 {
    public static void main(String args[]) {
        System.out.print("Input number of agents\"N\" and amount of coins for agent\"M\"");
        Scanner scanner = new Scanner(System.in);
        try {
            int N =scanner.nextInt();
            int M =scanner.nextInt();
            String[] strategies={"Naive", "Greedy", "Random"};
            final Random random=new Random();
            ArrayList<Agent> Participants=new ArrayList<Agent>(N);
            for(int i=0;i<N;i++){
                Participants.add(new Agent(M,strategies[i%2]));   //Change to strategies[random.nextInt(3)] for random
                System.out.print(Participants.get(i).getStrategy()+" ");   //Shows all picked strategies at the start
            }
            System.out.println(" "); //Uncomment to separate strategies list from winner
            while (Participants.size()>=2){
                for(int i=0;i<=Participants.size()-2;i++){
                    for(int i1=i+1;i1<=Participants.size()-1;i1++){
                        Participants.get(i).setHasPutMoney(Participants.get(i).getStrategy());
                        Participants.get(i1).setHasPutMoney(Participants.get(i1).getStrategy());
                        if (Participants.get(i).getHasPutMoney() && Participants.get(i1).getHasPutMoney()){    //Both put coins
                            Participants.get(i).setCoins(Participants.get(i).getCoins()+2);
                            Participants.get(i1).setCoins(Participants.get(i1).getCoins()+2);
                        }
                        else if (!(Participants.get(i).getHasPutMoney()) && !(Participants.get(i1).getHasPutMoney())){    //Both didn't put coins
                            Participants.get(i).setCoins(Participants.get(i).getCoins()-1);
                            Participants.get(i1).setCoins(Participants.get(i1).getCoins()-1);
                        }
                        else {
                            if (Participants.get(i).getHasPutMoney()){       //First put coin
                                Participants.get(i).setCoins(Participants.get(i).getCoins()-1);
                                Participants.get(i1).setCoins(Participants.get(i1).getCoins()+3);
                            }
                            else{              //Second put coin
                                Participants.get(i1).setCoins(Participants.get(i1).getCoins()-1);
                                Participants.get(i).setCoins(Participants.get(i).getCoins()+3);
                            }
                        }
                        if (Participants.get(i1).getCoins()<0){
                            Participants.remove(i1);
                            i1--;
                            continue;
                        }
                        if (Participants.get(i).getCoins()<0){
                            Participants.remove(i);
                            i--;
                            break;
                        }
                    }
                    /*for(int j=0;j<Participants.size();j++){                                                 //
                        System.out.print(Participants.get(j).getStrategy()+Participants.get(j).getCoins());   // Shows turns(Strategy&balance)
                    }                                                                                         //
                    System.out.println(""); */
                }
            }
            System.out.print(Participants.get(0).getStrategy());
        } catch (Exception e) {
            System.out.print("No values input");
        }
    }
}