
//This class is essentially a node for an array of tickets.
public class Ticket
{
    //Initialized to ERROR so it's obvious if they weren't reset by constructor.
    private String playerName = "ERROR";
    private String playerID = "ERROR";
    
    private int ticketNumber = -1;
    
    private int priorDraws = -1;
    
    public Ticket(String player, String ID, int number)
    {
        playerName = player;
        playerID = ID;
        ticketNumber = number;
    }
    
    public String getName()
    {
        return playerName;
    }
    
    public String getID()
    {
        return playerID;
    }
    
    public int getTicketNumber()
    {
        return ticketNumber;
    }
    
    public int getPriorDraws()
    {
        return priorDraws;
    }
    
    public void setPriorDraws(int priors)
    {
        priorDraws = priors;
    }
    
    public String toString()
    {
        StringBuilder returnString = new StringBuilder();
        
        if(!playerID.equalsIgnoreCase("ERROR"))
        {
            returnString.append("#" + playerID + " ");
        }
        returnString.append("@" + playerName + ", ticket #" + ticketNumber);
        if(priorDraws > -1)
        {
            returnString.append(" after " + priorDraws + " redraws");
        }
        
        return returnString.toString();
    }
    
    public boolean equals(Object comparison)
    {
        if(comparison == null || comparison.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            if (!playerID.equalsIgnoreCase("ERROR"))
            {
                return (this.playerID.equals(((Ticket)comparison).getID()));
            }
            else{
                return (this.playerName.equalsIgnoreCase(((Ticket)comparison).getName()));
            }
        }
    }
}
