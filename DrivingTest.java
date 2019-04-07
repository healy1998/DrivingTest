import java.util.*;
import java.io.*;	
import javax.swing.*;
import java.time.*;
import java.time.format.*;
public class DrivingTest
{
	public static String login(String userType) throws IOException
	{
		Scanner console = new Scanner(System.in);
		int attempts = 0; //initializes an attempts value of int type with the value of 0
		String aLineFromFile;
		String userInputPass; 
		String line;
		String linePass;
		String lineUser; //initializes strings
		int lineNumber = 0; //initializes linenumber value of int type with the value of 0
		String userInputUser = ""; //initialises string for later use
		String[] lineArray; //initialises string array
		String loginString = "false"; //sets string value as false so it's always false until user succeeds in logging in
		Boolean login = false;
		String lineUserID = "";
		String lineUserType = ""; //the format of the users file has the info on whether or not each user is an admin, the type of user to be checked is gotten through the method call.
		while (attempts < 3 && login == false) 			// gives you three attempts to login
		{
		    FileReader aFileReader = new FileReader("Admins.txt");
	     	Scanner in = new Scanner(aFileReader);
    		System.out.print("Username: ");
			userInputUser = console.nextLine(); 	// Inputted username
			System.out.print("Password: ");
			userInputPass = console.nextLine(); 	// Inputted password
			
			while(in.hasNext() && !login)
			{	
				line = in.nextLine();			//declares value as next line in 
				lineArray = line.split(","); 	//splits the read line from passwords.txt into an array at every ","
				lineUserID = lineArray[0];
				lineUser = lineArray[1];		//format of passwords.txt suggests username at position 1 in split array
				linePass = lineArray[2];		//format of passwords.txt suggests password at position 2 in split array
				lineUserType = lineArray[3];
				if(userInputUser.equalsIgnoreCase(lineUser) && userInputPass.equalsIgnoreCase(linePass) && userType.equals(lineUserType)) //if the user inputted username (ignoring case) matches the one on the line and the user inputted password too, also checks if the called user type matches the usertype of the one on the line
				{
		    		login = true; //telling the system the login succeeded
			    }
			}
		
			if (login == false) //if nothing has changed about the login value (i.e failed to login but there are remaining attempts)
			{
				System.out.println("error: password or username incorrect");	//error message
				attempts++; //increases attempts value by 1
				System.out.println("You have " + (3 - attempts) + " remaining attempts"); // displays how many attempts are left
			}
			
		}
		if (login == true)			//if the previous login succeeds
		{
			loginString = "true"; //since return needs to be in string, makes the string to be returned return true
		}
		loginString += "," + userInputUser + "," + lineUserID; //also returns the users name and ID
		return loginString;		//returns true if login was ever valid and false if all 3 attempts failed.
	}
	
	public static void ViewResults() throws IOException
	{
		FileReader reader =  new FileReader("results.txt");
		Scanner in = new Scanner(reader);
		while(in.hasNextLine())
		{
			System.out.println(in.nextLine());
		}
	}
	
	
	public static void main(String [] args)throws IOException
	{
		boolean open1 = true; // used for keeping you in menu
		boolean open2 = true; // used for keeping you in menu
		int choice = 0; 
		String[] loginInfo;
		while( open1 == true) // keeps you in the menu unless you exit
		{
			Scanner Mario = new Scanner(System.in); //scanner
			System.out.println("What would you like to do?\n1. Log in as admin\n2. Exit");
			if(Mario.hasNextInt()) //checks to see if input is an int
			{
				choice	= Mario.nextInt(); //registers the users choice through input
			}	
			if(choice == 1) // Login admin
			{
				loginInfo = login("admin").split(",");
				if (loginInfo[0].equals("true"))
				{
				    open2 = true;
			    	while( open2 == true)
		    		{
	    		
    					System.out.println("What would you like to do?\n1. Take a test.\n2. View Results\n3. Exit");
						if(Mario.hasNextInt()) //checks to see if input is an int
						{
					   		choice	= Mario.nextInt(); //registers the users choice through input
				    	}
			    		if(choice == 1) //takes the test
		    			{
	                    Scanner console = new Scanner(System.in);
    	                String UserInput;
      		            int correct = 0;
    	                int rng[] =  new int[10];		                    
						int rngNo;
		                boolean exists = false;
		                String[] Qs = {"Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7", "Q8", "Q9", "Q10", "Q11", "Q12", "Q13", "Q14", "Q15", "Q16", "Q17", "Q18", "Q19", "Q20", "Q21", "Q22", "Q23", "Q24", "Q25", "Q26", "Q27", "Q28", "Q29", "Q30"};
                        String[] tenQs = new String[10];
    	   	            for(int i = 0; i<10; i++)
	                    {
		                    exists = false;
		                    rngNo = (int)(Math.random()*30);
							for(int j = 0; j<rng.length; j++)
		                    {
			                    if(rngNo == rng[j])
   		    		            {
									exists = true;
    		    	            }
    			            }  
			                if(exists == true)
        		            {
		       	    	        i--;
        		            }
		       	            else
		                    {
			                    rng[i] = rngNo;
	            	        }
       	    	        }   
                	    for(int i = 0; i < rng.length; i++)
	    	            {
    	                    tenQs[i] = Qs[rng[i]];
		                }
	                    String Q1 = "When must you dip your headlights?";
	                    String A1 = "When following close behind other traffic. When meeting other traffic. In bad weather conditions such as dense fog or falling snow. At the beginning and end of lighting up times. In built up or special speed limit areas except unlit-up roads. On well lit roads outside speed limit areas";
            	        String Q2 = "What is the rule for box junctions?";
	    	            String A2 = "You must not enter the box area unless you can clear it without stopping. The one exception is if you are turning right provided you do not block other traffic which has a right to proceed";
                  	    String Q3 = "What are the three times you can overtake on the left";
		                String A3 = "When the driver in front has moved out and given a right hand turn signal and you intend to go straight ahead or turn left. When turning left and having signaled this intention. When the traffic in the lane to your right is moving more slowly than the traffic in your lane";
	       	            String Q4 = "What is the simple rule about keeping your distance";
           		        String A4 = "Allow 1 metre stopping distance per km/h of speed butthis should be doubled for wet and icy conditions";
	   	                String Q5a = "What is the general speed limit"; 
	                    String A5a = "100km/h limit applies to all roads not subject to a lower limit";
						String Q5b = "What is the speed limit of a motorway";
						String A5b = "120km/h";
	                    String Q6a = "What does a clearway mean?";
						String A6a = "A clearway is indicated by a red X surrounded by a red circle with an information plate underneath showing times when stopping or parking is prohibited by any vehicle except for traffic reasons. Buses or taxis are allowed to pick up and set down passengers, goods may be loaded or unloaded";
						String Q6b = "What does a single yellow line mean";
						String A6b = "It means no parking is prohibited during business hours";
						String Q6c = "What does a double yellow line mean";
						String A6c = "No parking at any time";
            		    String Q7 = "Describe a zebra crossing";
        	            String A7 = "It is marked by yellow flashing beacons. The actual crossing area is marked by black and white zebra strips. There may be a stop line and zigzag lines may also be provided";
    	                String Q8 = "Describe a pelican?";
		                String A8 = "It is a type of pedestrian traffic light. At these crossings an amber light will flash for a short period after the red light for drivers goes out proceed with caution if the way is clear";
                        String Q9 = "What are the road markings for a no-entry to a one way street?";
  	    	            String A9 = "A continuous white line across the road with a broken white line behind it, with no entry regulatory sign";
            	        String Q10 = "Where would you find a broken yellow line and what is the area inside this lane called and its purpose";
    	    	        String A10 = "The edge of carraigeway, hard shoulder, cyclists and pedestrians to move out of the way of a following vehicle if the way is clear - This is not allowed on a motorway";
    	                String Q11 = "When must you yield to a pedestrian?";
		                String A11 = "On a pedestrian crossing, zebra crossing, on a pelican crossing when the amber light is flashing. Crossing the road if you are moving off from a stationary position. At a junction if they have commenced crossing. Drivers must not put pedestrians at risk";
             		    String Q12 = "What should you not do if someone is about to overtake you?";
        	            String A12 = "Accelerate or change direction";
    	                String Q13 = "If a car is coming against you with full lights what do you do?";
		                String A13 = "Slow down and look towards the verge until the car has passed";
	    			    String Q14 = "Name three persons you must stop for";
                        String A14 = "Garda, School Warden or a person in charge of animals";
                        String Q15 = "Where would you find flashing red lights?";
	                    String A15 = "At an unattended level crossing";
	                    String Q16 = "What does a red triangle indicate";
    	                String A16 = "Accident, Breakdown, Hazard ahead";
 		                String Q17 = "What restrictions are there in relation to the use of a horn?";
	           	        String A17 = "The horn must not be used between 11:30pm and 7:00am in a built up area, except in an emergency";
	        	        String Q18 = "What is the rule for continuous white line?";
        	            String A18 = "All traffic must keep to the left of the white line except in an emergency or for access";
		                String Q19 = "Name five places you should not park";
    	                String A19 = "Within 15 metres of either side of a pedestrian crossing where the road is a two-way. Opposite a car on a narrow road. Within 5m of a junction unless facilities are provided. Taxi rank, Cycle track, Bus stop";
        	            String Q20 = "Who is responsible for ensuring that seat belts are worn?";
						String A20 = "The driver is responsible for children under 17. Over 17's are responsible for themselves";
    	   	            String Q21 = "When can you stop on a motorway?";
	   	                String A21 = "Breakdown, emergency, i.e accident or when signaled by gardai";
       		            String Q22 = "At an un-controlled junction of equal importance, to whom would you give way?";
	   	                String A22 = "Traffic on the right and traffic already turning";
						String Q23 = "When should you not overtake?";
		                String A23 = "At a bend, the brow of a hill, hump-back bridge at a continuous white line, or anywhere your view of on-coming traffic is restricted";
	       	            String Q24 = "What is aquaplaning?";
           		        String A24 = "It is when a film of water builds up between tyres and the road at high speed which reduces the area of contact with the road and will adversely affect control of the vehicle";
	   	                String Q25 = "At a stop sign which has no white, where would you stop?"; 
	                    String A25 = "at the stop sign";
	                    String Q26 = "What position would you take up for a right turn in a one way street?";
						String A26 = "The extreme right hand lane";
            		    String Q27 = "What does a green light mean";
        	            String A27 = "Go, provided it is safe to do so";
    	                String Q28 = "What is the legal parking distance from the kerb?";
		                String A28 = "18 inches";
                        String Q29 = "What should you look out for on a dark country road?";
  	    	            String A29 = "Cyclists & pedestrians";
            	        String Q30 = "If a pedestrian crossing has an island in the middle what does it mean?";
						String A30 = "Either side of the island is a seperate crossing";
	                    for(int i=0; i<rng.length; i++)
	                    {
	                        if(tenQs[i].equals("Q1"))
                            {
	    		                System.out.println(Q1 + "\t");
        			            UserInput = console.nextLine();
	    	     	            if(UserInput.equalsIgnoreCase(A1))
		        	            {
	    			                System.out.println("Congratulations, you are correct");
    			                    correct++;
		                        }
		                        else
		                        {
									System.out.println("Sorry, that is incorrect,the correct answer is " + A1);
	   	    				    }
    		                }
		                    else if(tenQs[i].equals("Q2"))
		                    {
			                    System.out.println(Q2 + "\t");
              			        UserInput = console.nextLine();
	       	        	        if(UserInput.equalsIgnoreCase(A2))
       			                { 
      		    		            System.out.println("Congratulations, you are correct");
	        	    	            correct++;
	    	                    }
		                        else
  			                    {
			    	                System.out.println("Sorry, that is incorrect,the correct answer is " + A2);
		    	                }
                	        }
            	            else if(tenQs[i].equals("Q3"))
	                        {
               			        System.out.println(Q3 + "\t");
		               	        UserInput = console.nextLine();
		                        if(UserInput.equalsIgnoreCase(A3))
       	    	     	        {
	                		        System.out.println("Congratulations, you are correct");
               			        	correct++;
   	             		        }
    	        	            else
        		    	        {
	    		                    System.out.println("Sorry, that is incorrect,the correct answer is " + A3);
        	    	       	    }
    	                    }
         	                else if(tenQs[i].equals("Q4"))
               	    	    {
    	               	    	System.out.println(Q4 + "\t");
	    	       	            UserInput = console.nextLine();
           		            	if(UserInput.equalsIgnoreCase(A4))
	                	        {
       	    		             	System.out.println("Congratulations, you are correct");
       	     			            correct++;
               		    	    }
	            	            else
    		          	        {
        	               			System.out.println("Sorry, that is incorrect,the correct answer is " + A4);
		                       	}
	            	        }
                       		else if(tenQs[i].equals("Q5"))
            	           	{
    	               	        System.out.println(Q5a + "\t");
            	   	    	    UserInput = console.nextLine();
	    	           	        if(UserInput.equalsIgnoreCase(A5a))
        		                {
             	    	    	    System.out.println("Congratulations, you are correct but " + Q5b + "\t");
									UserInput = console.nextLine();
									if(UserInput.equalsIgnoreCase(A5b))
									{
										System.out.println("Congratulations you are correct");
										correct++;
									}
									else
									{
										System.out.println("Sorry, that is incorrect, the correct answer is " + A5b);
									}
    	                	    	
    	    	                }
		        	            else
            	        	    {
        	               			System.out.println("Sorry, that is incorrect,the correct answer is " + A5a);
	        		            }
            	            }
            	           	else if(tenQs[i].equals("Q6"))
	                        {
		            	        System.out.println(Q6a + "\t");
             	                UserInput = console.nextLine();
        	               		if(UserInput.equalsIgnoreCase(A6a))
    	        	            {
            		   	        	System.out.println("Congratulations, you are correct but " + Q6b);
    		        	            UserInput = console.nextLine();
									if(UserInput.equalsIgnoreCase(A6b))
									{
										System.out.println("Congratulations you are correct but " + Q6c);
										UserInput = console.nextLine();
										if(UserInput.equalsIgnoreCase(A6c))
										{
											System.out.println("Congratulations you are correct");
											correct++;
										}
										else
										{
											System.out.println("Sorry, that is incorrect, the correct answer is " + A6c);
										}
									}
									else
									{
										System.out.println("Sorry, that is incorrect, the correct answer is " + A6b);
									}
        			            }
	        		            else
		                        {
        	           		    	System.out.println("Sorry, that is incorrect,the correct answer is " + A6a);
    	                   		}
	                   	    }
               		        else if(tenQs[i].equals("Q7"))
        	       	        {
	    	                    System.out.println(Q7 + "\t");
        	        	        UserInput = console.nextLine();
	            	            if(UserInput.equalsIgnoreCase(A7))
   		    	                {
        	    		            System.out.println("Congratulations, you are correct");
	    	        		        correct++;
    	    		            }
        			            else
	    		                {
		                            System.out.println("Sorry, that is incorrect,the correct answer is " + A7);
			                    }
    	                    }
	   	                    else if(tenQs[i].equals("Q8"))
               	    	    {
       		             	    System.out.println(Q8 + "\t");
   	        		            UserInput = console.nextLine();
               	 		        if(UserInput.equalsIgnoreCase(A8))
   		            	        {
				                    System.out.println("Congratulations, you are correct");
				                    correct++;
        		    	        }
    		               	    else
	        	                {
                  				    System.out.println("Sorry, that is incorrect,the correct answer is " + A8);
    		           	        }
	    	                }
           		            else if(tenQs[i].equals("Q9"))
    	                    {
	   		                    System.out.println(Q9 + "\t");
	        	                UserInput = console.nextLine();
	    	                    if(UserInput.equalsIgnoreCase(A9))
	    	    	            {
    	    			            System.out.println("Congratulations, you are correct");
    				                correct++;
        			            } 
		        	            else
			                    {
                    		        System.out.println("Sorry, that is incorrect,the correct answer is " + A9);
                         		}
	               	        }
	       	                else if(tenQs[i].equals("Q10"))
       		                {
   			                    System.out.println(Q10 + "\t");
		                        UserInput = console.nextLine();
								if(UserInput.equalsIgnoreCase(A10))
			    		    	{
	        		                System.out.println("Congratulations, you are correct");
	    	        	            correct++;
    		                    }
		                        else
	    	                    {
       			    	            System.out.println("Sorry, that is incorrect,the correct answer is " + A10);
	   	    			    	}
	                        }
    	                    else if(tenQs[i].equals("Q11"))
    		                    {
			                    System.out.println(Q11 + "\t");
                	    	    UserInput = console.nextLine();
	        	          	    if(UserInput.equalsIgnoreCase(A11))
    		                    {
            	 	    		    System.out.println("Congratulations, you are correct");
    		          	        	correct++;
		                        }
		                        else
       			                {
	    		        	        System.out.println("Sorry, that is incorrect,the correct answer is " + A11);
		            	        }
                 	        }
	        	            else if(tenQs[i].equals("Q12"))
        		            {
            		            System.out.println(Q12 + "\t");
	                    	    UserInput = console.nextLine();
	    	                    if(UserInput.equalsIgnoreCase(A12))
       	            		    {
	                    		    System.out.println("Congratulations, you are correct");
            			           	correct++;
        	    		        }
    		    	            else
        			            {
	        		  	            System.out.println("Sorry, that is incorrect,the correct answer is " + A12);
            	   	        	}
    	                   	}
	                        else if(tenQs[i].equals("Q13"))
            	    	    {
                	       		System.out.println(Q13 + "\t");
	            	            UserInput = console.nextLine();
                	        	if(UserInput.equalsIgnoreCase(A13))
    		               	    {
    		                	    System.out.println("Congratulations, you are correct");
	         		                correct++;
           		    	        }
       		            	    else
           		    	        {
           	        		    	System.out.println("Sorry, that is incorrect,the correct answer is " + A13);
	                        	}
	        	            }
                	    	else if(tenQs[i].equals("Q14"))
             	          	{
	                	        System.out.println(Q14 + "\t");
                    	    	UserInput = console.nextLine();
         		           	    if(UserInput.equalsIgnoreCase(A14))
        		                {
        	   	         	    	System.out.println("Congratulations, you are correct");
		               	        	correct++;
	                            }
    	   		                else
                   			    {
       	                			System.out.println("Sorry, that is incorrect,the correct answer is " + A14);
	       	     	            }
           	            	}
           	            	else if(tenQs[i].equals("Q15"))
            	            {
		            	        System.out.println(Q15 + "\t");
            	    	 	    UserInput = console.nextLine();
        	        	        if(UserInput.equalsIgnoreCase(A15))
	    		                {
            		        	    System.out.println("Congratulations, you are correct");
	    	                    	correct++;
    	        	            }
	        	   	            else
	    	                    {
                		        	System.out.println("Sorry, that is incorrect,the correct answer is " + A15);
    	               		    }
	                   	    }
                   		    else if(tenQs[i].equals("Q16"))
               	        	{
	       		                System.out.println(Q16 + "\t");
       	    	            	UserInput = console.nextLine();
	                    	    if(UserInput.equalsIgnoreCase(A16))
   		    	                {
        	    		            System.out.println("Congratulations, you are correct");
									correct++;
     	    		            }
        			            else
    	    		            {
		    	                    System.out.println("Sorry, that is incorrect,the correct answer is " + A16);
			                    }
    	    	            }
	        	            else if(tenQs[i].equals("Q17"))
                            {
	    	         	        System.out.println(Q17 + "\t");
		     	                UserInput = console.nextLine();
        	        	  	    if(UserInput.equalsIgnoreCase(A17))
		                        {
	    		    	            System.out.println("Congratulations, you are correct");
    		    		            correct++;
            			        }
	    	        	        else
    			                {
        		    		        System.out.println("Sorry, that is incorrect,the correct answer is " + A17);
    		        	        }
	        	            }
                		    else if(tenQs[i].equals("Q18"))
    		                {
		                        System.out.println(Q18 + "\t");
		                        UserInput = console.nextLine();
	    	                    if(UserInput.equalsIgnoreCase(A18))
    	    		            {
	        			            System.out.println("Congratulations, you are correct");
 		    		                correct++;
     	    		            }
	                	        else
		                        {
    	    	    		        System.out.println("Sorry, that is incorrect,the correct answer is " + A18);
         	            		}
	                 	    }
    	    	    		else if(tenQs[i].equals("Q19"))
    		                {
			                    System.out.println(Q19 + "\t");
		                        UserInput = console.nextLine();
		                        if(UserInput.equalsIgnoreCase(A19))
    	        	            {
	           			            System.out.println("Congratulations, you are correct");
	            		            correct++;
                    		    }
		                        else
			                    {
            		        	    System.out.println("Sorry, that is incorrect,the correct answer is " + A19);
								}
                 	        }
	        	    		else if(tenQs[i].equals("Q20"))
	       	                {
       			                System.out.println(Q20 + "\t");
    		                    UserInput = console.nextLine();
    		                    if(UserInput.equalsIgnoreCase(A20))
	    	                    {
		    	   	                System.out.println("Congratulations, you are correct");
			           	            correct++;
            	        	    }
	    	          	        else
    	    		            {
                			     System.out.println("Sorry, that is incorrect,the correct answer is " + A20);
            	        	    }
	          	            }
			         	    else if(tenQs[i].equals("Q21"))
		                    {
		    	                System.out.println(Q21 + "\t");
	    	    	            UserInput = console.nextLine();
            			        if(UserInput.equalsIgnoreCase(A21))
	        		            {
    		    		            System.out.println("Congratulations, you are correct");
			    	                correct++;
        		        	    }
		            	        else
		    	                {
            		    		    System.out.println("Sorry, that is incorrect,the correct answer is " + A21);
        	            		}
	      	                }
							else if(tenQs[i].equals("Q22"))
		                    {
			                    System.out.println(Q22 + "\t");
              			        UserInput = console.nextLine();
	       	        	        if(UserInput.equalsIgnoreCase(A22))
       			                { 
      		    		            System.out.println("Congratulations, you are correct");
	        	    	            correct++;
	    	                    }
		                        else
  			                    {
			    	                System.out.println("Sorry, that is incorrect,the correct answer is " + A22);
		    	                }
                	        }
            	            else if(tenQs[i].equals("Q23"))
	                        {
               			        System.out.println(Q23 + "\t");
		               	        UserInput = console.nextLine();
		                        if(UserInput.equalsIgnoreCase(A23))
       	    	     	        {
	                		        System.out.println("Congratulations, you are correct");
               			        	correct++;
   	             		        }
    	        	            else
        		    	        {
	    		                    System.out.println("Sorry, that is incorrect,the correct answer is " + A23);
        	    	       	    }
    	                    }
         	                else if(tenQs[i].equals("Q24"))
               	    	    {
    	               	    	System.out.println(Q24 + "\t");
	    	       	            UserInput = console.nextLine();
           		            	if(UserInput.equalsIgnoreCase(A24))
	                	        {
       	    		             	System.out.println("Congratulations, you are correct");
       	     			            correct++;
               		    	    }
	            	            else
    		          	        {
        	               			System.out.println("Sorry, that is incorrect,the correct answer is " + A24);
		                       	}
	            	        }
                       		else if(tenQs[i].equals("Q25"))
            	           	{
    	               	        System.out.println(Q25 + "\t");
            	   	    	    UserInput = console.nextLine();
	    	           	        if(UserInput.equalsIgnoreCase(A25))
        		                {
             	    	    	    System.out.println("Congratulations, you are correct");
    	                	    	correct++;
    	    	                }
		        	            else
            	        	    {
        	               			System.out.println("Sorry, that is incorrect,the correct answer is " + A25);
	        		            }
            	            }
            	           	else if(tenQs[i].equals("Q26"))
	                        {
		            	        System.out.println(Q26 + "\t");
             	                UserInput = console.nextLine();
        	               		if(UserInput.equalsIgnoreCase(A26))
    	        	            {
            		   	        	System.out.println("Congratulations, you are correct");
    		        	            correct++;
        			            }
	        		            else
		                        {
        	           		    	System.out.println("Sorry, that is incorrect,the correct answer is " + A26);
    	                   		}
	                   	    }
               		        else if(tenQs[i].equals("Q27"))
        	       	        {
	    	                    System.out.println(Q27 + "\t");
        	        	        UserInput = console.nextLine();
	            	            if(UserInput.equalsIgnoreCase(A27))
   		    	                {
        	    		            System.out.println("Congratulations, you are correct");
	    	        		        correct++;
    	    		            }
        			            else
	    		                {
		                            System.out.println("Sorry, that is incorrect,the correct answer is " + A27);
			                    }
    	                    }
	   	                    else if(tenQs[i].equals("Q28"))
               	    	    {
       		             	    System.out.println(Q28 + "\t");
   	        		            UserInput = console.nextLine();
               	 		        if(UserInput.equalsIgnoreCase(A28))
   		            	        {
				                    System.out.println("Congratulations, you are correct");
				                    correct++;
        		    	        }
    		               	    else
	        	                {
                  				    System.out.println("Sorry, that is incorrect,the correct answer is " + A28);
    		           	        }
	    	                }
           		            else if(tenQs[i].equals("Q29"))
    	                    {
	   		                    System.out.println(Q29 + "\t");
	        	                UserInput = console.nextLine();
	    	                    if(UserInput.equalsIgnoreCase(A29))
	    	    	            {
    	    			            System.out.println("Congratulations, you are correct");
    				                correct++;
        			            } 
		        	            else
			                    {
                    		        System.out.println("Sorry, that is incorrect,the correct answer is " + A29);
                         		}
	               	        }
	       	                else if(tenQs[i].equals("Q30"))
       		                {
   			                    System.out.println(Q30 + "\t");
		                        UserInput = console.nextLine();
								if(UserInput.equalsIgnoreCase(A30))
			    		    	{
	        		                System.out.println("Congratulations, you are correct");
	    	        	            correct++;
    		                    }
		                        else
	    	                    {
       			    	            System.out.println("Sorry, that is incorrect,the correct answer is " + A30);
	   	    			    	}
	                        }
		                }
	    	            System.out.println("You have gotten " + correct + " answers correct out of ten");
						FileWriter writer  = new FileWriter("results.txt", true);
						PrintWriter out = new PrintWriter(writer);
						LocalTime userTime = LocalTime.now();
						LocalDate today = LocalDate.now();
						out.println("You received a score of " + correct + " out of 10 on " + today + " at " + userTime);
						out.close();
						}
						else if(choice == 2)//view results
						{
							ViewResults();
						}
						else if(choice == 3)//exit
						{
							open2 = false;
						}
						else
						{
							System.out.println("That is not a valid input");
					    }
					   
                    }
                }
		    }
			else if(choice == 2)
			{
		        open1 = false;
	    	    System.out.println("Goodbye");
   			}
		  	else
	    	{
				open2 = false;
    			System.out.println("Sorry that is not a valid input");
		    }
        }
    }
}