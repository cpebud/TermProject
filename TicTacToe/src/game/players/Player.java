package game.players;


public class Player
{
    private Symbol symbol;
    
    public Player()
    {
        
    }
    
    private void setSymbol(Symbol symbol)
    {
        this.symbol = symbol;
    }
    
    public Symbol getSymbol()
    {
        return symbol;
    }
    
    public void assignRandomSymbol()
    {
        int random = (int)(Math.random() * 2); // Randomly select 0 or 1
        if (random == 0)
        {
            setSymbol(Symbol.OH);
        }
        else
        {
            setSymbol(Symbol.EX);
        }
    }
    
    public enum Symbol
    {
        EX,
        OH;
    }
}
