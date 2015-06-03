package fiuba.algo3.algocraft;


public class Gas extends Entidad{

	public Gas(Gas gas) 
	{
		super(gas);
	}

	public Gas(Vector2D posicionGas, int dimension) 
	{
		super(posicionGas,dimension,dimension);			
	}	

}
