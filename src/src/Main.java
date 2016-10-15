import java.util.List;
import java.util.Random;
import java.io.*;

public class Main {
	
	private static Random random = new Random(); 
	private static int firstRandomCond = 0 ;
	private static int avgRandimCond = 0;
	private static boolean isThrow = false;
	private static int bet = 5;
	private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {		
			   
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();

        int totalWins = 0;
        int totalLosses = 0;

        while (true)
        {
            int winCount = 0;
            int loseCount = 0;
            
            // Since the loop is 100, we catch a random number from 0 to 99
            firstRandomCond = random.nextInt(100);
            
            for (int i = 0; i < 100; i++)
            {
            	String name = "Fred";
            	int balance = 100;
            	int limit = 0;
                player = new Player(name, balance);
                player.setLimit(limit);

                System.out.println(String.format("Start Game %d: ", i));
                System.out.println(String.format("%s starts with balance %d, limit %d", 
                		player.getName(), player.getBalance(), player.getLimit()));
                
                int turn = 0;
                while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
                {
                    turn++;                    
                	DiceValue pick = DiceValue.getRandom();
                   
                	System.out.printf("Turn %d: %s bet %d on %s\n",
                			turn, player.getName(), bet, pick); 
                	                	                    
                	int winnings = 0;
                	
                	// TODO: Special condition for no throw
                	if(firstRandomCond == i && avgRandimCond != 0 
                			&&   			avgRandimCond == turn 
                			&& isThrow == false)
                	{
                		winnings = -1;
                		isThrow = true;
                	}
                	else
                	{
                		winnings = game.playRound(player, pick, bet);
                	}
                    
                	cdv = game.getDiceValues();
                	                                
                    System.out.printf("Rolled %s, %s, %s\n",
                    		cdv.get(0), cdv.get(1), cdv.get(2));
                    
                    if (winnings > 0) {
	                    System.out.printf("%s won %d, balance now %d\n\n",
	                    		player.getName(), winnings, player.getBalance());
	                	winCount++; 
                    }
                    else if  (winnings == -1)  {
                    	player.receiveWinnings(bet);
	                    System.out.printf("%s No SPIN/THROW, balance now %d\n\n",
	                    		player.getName(), player.getBalance());
	                    
	                    console.readLine();
                    }
                    else
                    {
                    	System.out.printf("%s lost, balance now %d\n\n",
	                    		player.getName(), player.getBalance());
	                	loseCount++;
                    }
                    
                    
                } //while
                
                // Find average 
                avgRandimCond =random.nextInt(turn);
                
                System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
                System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

                
            } //for
            
            System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
            totalWins += winCount;
            totalLosses += loseCount;
                      
            System.out.println("To exit enter [Q], to replay press any key...");
            String ans = console.readLine();
            
            if (ans.toUpperCase().equals("Q"))
            {
            	break;
            }
            else
            {
            	// display bet menu
            	if(menu())
            		{
            			break;
            		};
            }
        } //while true
        
        System.out.println("totalWins: " + totalWins);
        System.out.println("totalLosses: " + totalLosses);
        System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
	}
	
	private static boolean menu() throws IOException
	{
		
		boolean isShowMenu = true;
		
		while(isShowMenu)
		{
			System.out.println("Please select a bet from below menu or press [Q] to quit:");
			System.out.println("1) \t 5$");
			System.out.println("2) \t 10$");
			System.out.println("3) \t 20$");
			System.out.println("4) \t 30$");
			System.out.println("5) \t 40$");
			System.out.println("6) \t 50$");
			System.out.println("7) \t 60$");
			System.out.println("8) \t 70$");
			System.out.println("9) \t 80$");
			System.out.println("10) \t 90$");
			System.out.println("11) \t 100$ \n");
			String ans = console.readLine();
			
			if(ans.toUpperCase().equals("Q"))
			{
				break;				
			}
			
			if (ans.matches("[0-9]+"))
			{
				int player_bet = Integer.valueOf(ans);
				
				if(player_bet > 11 || player_bet < 1)
				{
					System.out.println("Invalid bet, plrease re-enter:");
					
				}else
				{
					isShowMenu = false;
					
					switch (player_bet)
					{
					case 1:
						bet = 5;
						break;
					case 2:
						bet = 10;
						break;
					case 3:
						bet = 20;
						break;
					case 4:
						bet = 30;
						break;
					case 5:
						bet = 40;
						break;
					case 6:
						bet = 50;
						break;
					case 7:
						bet = 60;
						break;
					case 8:
						bet = 70;
						break;
					case 9:
						bet = 80;
						break;
					case 10:
						bet = 90;
						break;
					case 11:
						bet = 100;
						break;					
					}
				}
			}else
			{
				System.out.println("Invalid bet, plrease re-enter:");
			}
		}
		
		return isShowMenu;
	}

}
