import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RaffleIO
{
    public RaffleDrawing parseRaffle(String filename, RaffleSettings settings) throws FileNotFoundException
    {
        RaffleDrawing raffle = new RaffleDrawing(settings);

        //Let's get the files first.
        Scanner raffleReader = null;
        String[] raffleEntry = new String[raffle.settings.numberOfColumns]; //however many columns we have should be in here
        int raffleEntryNumber = 0; //number of tickets in that bundle
        raffleReader = new Scanner(new File(filename)); //if not found, GUI catches error
        
        //Here we're going to create our ticket list with tickets expanded.
        while(raffleReader.hasNextLine())
        {
            raffleEntry = raffleReader.nextLine().split(",");
            
            //If a noninteger is encountered, should be handled in GUI
            raffleEntryNumber = Integer.parseInt(raffleEntry[raffle.settings.ticketBundleRow].trim());
            //Arg list is player, ID, bundle number
            if(raffle.settings.usePlayerID)
            {
                raffle.addTicketBundle(raffleEntry[raffle.settings.nameRow], 
                                                   raffleEntry[raffle.settings.idRow], 
                                                   raffleEntryNumber);
            }
            //This will be using the player's name as the identifying feature
            else
            {
                raffle.addTicketBundle(raffleEntry[raffle.settings.nameRow], 
                        "ERROR", 
                        raffleEntryNumber);
            }
        }
        raffleReader.close();
        //List should now be assembled correctly with individual tickets
        
        return raffle;
    }
    
    public static void writeWinners(ArrayList<Ticket> winners, String filename)
    {
        BufferedWriter writer = null;
        try 
        {
            File raffleFile = new File(filename);
            
            writer = new BufferedWriter(new FileWriter(raffleFile, true));
            
            //StringBuilder winnersString = new StringBuilder();
            for(int i = 0; i < winners.size(); i++)
            {
                writer.write(winners.get(i).toString());
                writer.newLine();
            }
            writer.newLine();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                // Close the writer regardless of what happens
                writer.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}
