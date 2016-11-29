import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds all the tickets in the raffle, which are 
 * added externally. It also handles all of the randomization
 * logic in drawing. The drawn tickets should be handled by 
 * the calling class. The relevant settings are also stored 
 * in an object here which is public.
 *
 */
public class RaffleDrawing
{
    //Everyone should be able to check raffle settings
    public RaffleSettings settings;
    
    // This will hold all of our tickets.
    private ArrayList<Ticket> ticketList;
    // Always increment when new ticket added - this is the ID number of the
    // ticket
    private int currentTicketNumber;
    // Holding the current winners in case we care in settings
    private ArrayList<Ticket> allWinners;
    
    private long seed;
    private Random generator;
    
    public RaffleDrawing()
    {
        settings = new RaffleSettings();
        ticketList = new ArrayList<Ticket>();
        allWinners = new ArrayList<Ticket>();
        currentTicketNumber = 0;
    }
    public RaffleDrawing(int seed)
    {
        this();
        generator = new Random(seed);
    }
    public RaffleDrawing(RaffleSettings givenSettings)
    {
        this();
        settings = givenSettings;
    }
    
    public void addTicket(String player, String ID)
    {
        ticketList.add(new Ticket(player, ID, currentTicketNumber));
        currentTicketNumber++;
    }
    public void addTicketBundle(String player, String ID, int numberOfTickets)
    {
        for(int i=0; i < numberOfTickets; i++)
        {
            //Player, then ID, then ticket number
            ticketList.add(new Ticket(player, ID, currentTicketNumber));
            currentTicketNumber++;
        }
    }
    
    public void setSeed(long seed)
    {
        this.seed = seed;
        generator = new Random(seed);
    }
    
    public Ticket drawTicket()
    {
        if(ticketList.size() < 1)
        {
            return null;
        }
        
        Ticket drawn = ticketList.remove(generator.nextInt(ticketList.size()));
        return drawn;
    }
    public ArrayList<Ticket> drawBatch()
    {
        ArrayList<Ticket> winners = new ArrayList<Ticket>();
        Ticket potentialWinner = null;
        int redrawCount = 0;
        while(winners.size() < settings.batchSize)
        {
            potentialWinner = drawTicket();
            if(potentialWinner == null)
            {
                return winners;
            }
            //If we don't care about repeats or if it's a unique winner anyway
            if(!settings.noRepeats || !allWinners.contains(potentialWinner))
            {
                potentialWinner.setPriorDraws(redrawCount);
                allWinners.add(potentialWinner);
                winners.add(potentialWinner);
            }
            else
            {
                redrawCount++;
            }
        }
        return winners;
    }
    
    public ArrayList<Ticket> getTicketList()
    {
        return ticketList;
    }
    
    public String toString()
    {
        StringBuilder returnString = new StringBuilder();
        for(int i = 0; i < ticketList.size(); i++)
        {
            returnString.append(ticketList.get(i).toString());
            returnString.append("\n");
        }
        return returnString.toString();
    }
}