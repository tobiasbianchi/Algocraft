package fiuba.algo3.algocraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import fiuba.algo3.algocraft.Vector2D;

public class Mundo {
	
	private int divisionGrillaParaAnalisis;
	private int resolucion;
	private int divisionGrilla;
	private ArrayList<Mineral> mineral;
	private ArrayList<Gas> gas;
	private ArrayList<Espacio> espacio;
	private Vector2D baseJugador1;
	private Vector2D baseJugador2;
	
	public void generar()
	{
		double tamañoDivision = (double)resolucion/divisionGrillaParaAnalisis;
		
		baseJugador1 = new Vector2D(4*divisionGrilla,4*divisionGrilla);
		baseJugador2 = new Vector2D(resolucion-4*divisionGrilla,resolucion-4*divisionGrilla);
		
		for(int i=0;i<divisionGrillaParaAnalisis;i++)
			for(int a=0;a<divisionGrillaParaAnalisis;a++)
				for(int j=0;j<5;j++)
				{
					Vector2D posicionMineral = new Vector2D().aleatorio(tamañoDivision*i,tamañoDivision*(i+1)-divisionGrilla,tamañoDivision*a,tamañoDivision*(a+1)-divisionGrilla), posicionGas = new Vector2D().aleatorio(tamañoDivision*i,tamañoDivision*(i+1)-divisionGrilla,tamañoDivision*a,tamañoDivision*(a+1)-divisionGrilla);
					
					if(!hayMineral(posicionMineral) && !hayGas(posicionMineral) && baseJugador1.distintoA(pixelAGrilla(posicionMineral)) && baseJugador2.distintoA(pixelAGrilla(posicionMineral)))
						mineral.add(new Mineral(posicionMineral,divisionGrilla));	
					
					if(!hayMineral(posicionGas) && !hayGas(posicionGas) && baseJugador1.distintoA(pixelAGrilla(posicionGas)) && baseJugador2.distintoA(pixelAGrilla(posicionGas)))
						gas.add(new Gas(posicionGas,divisionGrilla));	
				}
		
	}

	public Vector2D pixelAGrilla(Vector2D posicion)
	{
		double posicionX = ((int)posicion.obtenerCoordenadaX()/divisionGrilla)*divisionGrilla, posicionY = ((int)posicion.obtenerCoordenadaY()/divisionGrilla)*divisionGrilla;
		
		return new Vector2D(posicionX,posicionY);		
	}

	public Mundo(int resolucion, int divisionGrilla)
	{
		
		if(resolucion < 0 || divisionGrilla < 0)
			throw new ParametroNegativo();
		
		if(resolucion == 0 || divisionGrilla == 0)
			throw new ParametroNulo();
		
		if(resolucion % divisionGrilla != 0)
			throw new DivisionDeGrillaNoEsMultiploDeResolucion();
		
		this.resolucion = resolucion;
		this.divisionGrilla = divisionGrilla;
		divisionGrillaParaAnalisis = 10;
		
		this.baseJugador1 = null;
		this.baseJugador2 = null;
		
		mineral = new ArrayList<Mineral>();
		gas = new ArrayList<Gas>();
		espacio = new ArrayList<Espacio>();
	}
	
	public boolean hayMineral(Vector2D posicion)
	{
		for(int i=0;i<mineral.size();i++)
			if(mineral.get(i).incluyeA(posicion))
				return true;
		return false;			
	}
	
	public boolean hayGas(Vector2D posicion)
	{
		for(int i=0;i<gas.size();i++)
			if(gas.get(i).incluyeA(posicion))
				return true;
		return false;	
	}
	
	public boolean hayEspacio(Vector2D posicion)
	{
		for(int i=0;i<espacio.size();i++)
			if(espacio.get(i).incluyeA(posicion))
				return true;
		return false;	
	}
	
	public Vector2D posicionBaseJugador1()
	{
		if(baseJugador1 == null)
			throw new posicionDeJugadorIndefinida();
		return new Vector2D(baseJugador1);
	}
	
	public Vector2D posicionBaseJugador2()
	{
		if(baseJugador2 == null)
			throw new posicionDeJugadorIndefinida();
		return new Vector2D(baseJugador2);
	}
	
	public ArrayList<Mineral> mineralesCercanos(Vector2D posicion)
	{
		ArrayList<Mineral> mineralOrdenadosPorDistancia = new ArrayList<Mineral>(), mineralesMasCercanos = new ArrayList<Mineral>();
		
		for(int i=0;i<mineral.size();i++)
			mineralOrdenadosPorDistancia.add(new Mineral(mineral.get(i)));
		
		class ClaseComparadoraLocal implements Comparator<Mineral>
		{
			private Vector2D referencia;
			
			public ClaseComparadoraLocal(Vector2D posicion) 
			{
				referencia = posicion;
			}

			public int compare(Mineral m1, Mineral m2)
			{
				Vector2D posicionM1 = m1.obtenerPosicion(), posicionM2 = m2.obtenerPosicion();
				
				if(posicionM1.distanciaA(referencia) < posicionM2.distanciaA(referencia))
					return -1;
				else if(posicionM1.distanciaA(referencia) > posicionM2.distanciaA(referencia))
					return 1;
				return 0;
			}
			
		
		}
		
		mineralOrdenadosPorDistancia.sort(new ClaseComparadoraLocal(posicion));
		
		for(int i=0;i<5;i++)
			mineralesMasCercanos.add(new Mineral(mineralOrdenadosPorDistancia.get(i)));	
		
		return mineralesMasCercanos;
		
	}
	
	public ArrayList<Gas> gasCercano(Vector2D posicion)
	{
		ArrayList<Gas> GasOrdenadosPorDistancia = new ArrayList<Gas>(), gasesMasCercanos = new ArrayList<Gas>();
		
		for(int i=0;i<gas.size();i++)
			GasOrdenadosPorDistancia.add(new Gas(gas.get(i)));
		
		class ClaseComparadoraLocal implements Comparator<Gas>
		{
			private Vector2D referencia;
			
			public ClaseComparadoraLocal(Vector2D posicion) 
			{
				referencia = posicion;
			}

			public int compare(Gas m1, Gas m2)
			{
				Vector2D posicionM1 = m1.obtenerPosicion(), posicionM2 = m2.obtenerPosicion();
				
				if(posicionM1.distanciaA(referencia) < posicionM2.distanciaA(referencia))
					return -1;
				else if(posicionM1.distanciaA(referencia) > posicionM2.distanciaA(referencia))
					return 1;
				return 0;
			}
			
		
		}
		
		GasOrdenadosPorDistancia.sort(new ClaseComparadoraLocal(posicion));
		
		for(int i=0;i<5;i++)
			gasesMasCercanos.add(new Gas(GasOrdenadosPorDistancia.get(i)));	
		
		return gasesMasCercanos;
		
	}	
	
	public int obtenerResolucion()
	{
		return resolucion;
	}

	public boolean hayMineralesCercanos(Vector2D posicion) 
	{
		int a = 0;
		ArrayList<Mineral> aux = mineralesCercanos(posicion);
		
		for(int i=0;i<aux.size();i++)
			if(aux.get(i).obtenerPosicion().distanciaA(posicion) < 2*resolucion/divisionGrillaParaAnalisis)
				a++;
		
		if(a > 2)
			return true;
		return false;
	}

	public boolean hayGasCercano(Vector2D posicion) 
	{
		int a = 0;
		ArrayList<Gas> aux = gasCercano(posicion);
		
		for(int i=0;i<aux.size();i++)
			if(aux.get(i).obtenerPosicion().distanciaA(posicion) < 2*resolucion/divisionGrillaParaAnalisis)
				a++;
		
		if(a > 2)
			return true;
		return false;
	}
}
