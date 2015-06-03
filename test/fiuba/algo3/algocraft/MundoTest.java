package fiuba.algo3.algocraft;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class MundoTest {
	
	@Test
	public void crearMundo() 
	{
		new Mundo(500,10);

	}
	
	@Test
	public void generarMundo() 
	{
		new Mundo(500,10).generar();
	}
	
	@Test(expected = ParametroNegativo.class)
	public void resolucionNegativa() 
	{
		new Mundo(-500,10);	
	}
	
	@Test(expected = ParametroNegativo.class)
	public void divisionDeGrillaNegativa() 
	{
		new Mundo(500,-10);	
	}
	
	@Test(expected = ParametroNulo.class)
	public void divisionDeGrillaCero()
	{
		new Mundo(500,0);	
	}
	
	@Test(expected = ParametroNulo.class)
	public void resolucionCero() 
	{
		new Mundo(0,10);	
	}
	
	@Test(expected = DivisionDeGrillaNoEsMultiploDeResolucion.class)
	public void divisionDeGrillaDistintoAMultiploDeResolucion() 
	{
		new Mundo(500,3);	
	}


	@Test
	public void posicionValidaBaseJugadores() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Vector2D posicionBaseJugador1 = mundo.posicionBaseJugador1(), posicionBaseJugador2 = mundo.posicionBaseJugador2();
		
		Assert.assertTrue( 0 < posicionBaseJugador1.obtenerCoordenadaX() && posicionBaseJugador1.obtenerCoordenadaX() < mundo.obtenerResolucion());
		Assert.assertTrue( 0 < posicionBaseJugador1.obtenerCoordenadaY() && posicionBaseJugador1.obtenerCoordenadaY() < mundo.obtenerResolucion());
		
		Assert.assertTrue( 0 < posicionBaseJugador2.obtenerCoordenadaX() && posicionBaseJugador2.obtenerCoordenadaX() < mundo.obtenerResolucion());
		Assert.assertTrue( 0 < posicionBaseJugador2.obtenerCoordenadaY() && posicionBaseJugador2.obtenerCoordenadaY() < mundo.obtenerResolucion());
		
	}
	
	@Test
	public void distanciaEntreBasesDeJugadoresGrande() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Vector2D posicionBaseJugador1 = mundo.posicionBaseJugador1(), posicionBaseJugador2 = mundo.posicionBaseJugador2();
		
		Assert.assertTrue(posicionBaseJugador1.distanciaA(posicionBaseJugador2) >= mundo.obtenerResolucion()*0.75);
	}
	
	@Test
	public void recursosCercanosALasBasesDeLosJugares() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Vector2D posicionBaseJugador1 = mundo.posicionBaseJugador1(), posicionBaseJugador2 = mundo.posicionBaseJugador2();
		
		Assert.assertTrue( mundo.hayMineralesCercanos(posicionBaseJugador1));
		Assert.assertTrue( mundo.hayGasCercano(posicionBaseJugador1));
		
		Assert.assertTrue( mundo.hayMineralesCercanos(posicionBaseJugador2));
		Assert.assertTrue( mundo.hayGasCercano(posicionBaseJugador2));
		
	}
	
	@Test
	public void listaDeMineralesCercanos() 
	{

		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Assert.assertTrue(mundo.mineralesCercanos(new Vector2D().aleatorio(0, mundo.obtenerResolucion(),0,mundo.obtenerResolucion())).size() > 0);
	}
	
	@Test
	public void listaDeGasesCercanos() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Assert.assertTrue(mundo.gasCercano(new Vector2D().aleatorio(0, mundo.obtenerResolucion(),0,mundo.obtenerResolucion())).size() > 0);
	}
	
	@Test
	public void obtenerResolucion() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Assert.assertEquals(500, mundo.obtenerResolucion());
	}
	
	@Test(expected = posicionDeJugadorIndefinida.class)
	public void posicionBaseJugador1Vacia() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.posicionBaseJugador1();
	}
	
	@Test(expected = posicionDeJugadorIndefinida.class)
	public void posicionBaseJugador2Vacia()
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.posicionBaseJugador2();
	}
	
	@Test
	public void posicionBaseJugador1NoVacia() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		mundo.posicionBaseJugador1();
	}
	
	@Test
	public void posicionBaseJugador2NoVacia() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		mundo.posicionBaseJugador2();
	}
	
	@Test
	public void posicionDondeNoHayMineral() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Assert.assertFalse(mundo.hayMineral(mundo.posicionBaseJugador1()));
	}
	
	@Test
	public void posicionDondeSiHayMineral() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		ArrayList<Mineral> minerales = mundo.mineralesCercanos(new Vector2D().aleatorio(0, mundo.obtenerResolucion(),0,mundo.obtenerResolucion()));
		
		Assert.assertTrue(mundo.hayMineral(minerales.get(0).obtenerPosicion()));
	}
	
	@Test
	public void posicionDondeNoHayGas() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Assert.assertFalse(mundo.hayGas(mundo.posicionBaseJugador2()));
	}
	
	@Test
	public void posicionDondeSiHayGas() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		ArrayList<Gas> gas = mundo.gasCercano(new Vector2D().aleatorio(0, mundo.obtenerResolucion(),0,mundo.obtenerResolucion()));
		
		Assert.assertTrue(mundo.hayGas(gas.get(0).obtenerPosicion()));
	}
	
	@Test
	public void posicionDondeNoHayEspacio() 
	{
		Mundo mundo = new Mundo(500,10);
		
		mundo.generar();
		
		Assert.assertFalse(mundo.hayEspacio(mundo.posicionBaseJugador1()));
	}
	
	@Test
	public void pixelAGrilla() 
	{
		Mundo mundo = new Mundo(500,10);
		
		Vector2D grilla = mundo.pixelAGrilla(new Vector2D(1,1));
		
		Assert.assertTrue(grilla.obtenerCoordenadaX() == 0);
		Assert.assertTrue(grilla.obtenerCoordenadaY() == 0);
	}
	
	
}
