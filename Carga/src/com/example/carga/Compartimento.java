package com.example.carga;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.carga.R;

public class Compartimento extends Fragment {

	String Nombre;
	boolean Ocupado;
	int CantSillas, SillasSup, SillasInf, SillasCentro, PosYSup, PosYInf, PosYCentro1, 
		PosYCentro2, AnchoAvion, AltoAvion, AnchoSilla, AltoSilla, PosX0Inf, PosX0Sup,
		PosX0Centro, Fila,SillasOcupadas;
	int [] ContFila;
	int[][] PosX0, DispSillas;
	MySilla[] Sillas;
	FrameLayout General;
	FrameLayout.LayoutParams[] lpSillas;
	Drawable DwSilla;
	private CambioPasajeros cambiopasajeros;
	public Context context;
	
	//private Handler mHandler=new Handler();
	
	
	
	
	public int getSillasOcupadas() {
		return SillasOcupadas;
	}

	public void setSillasOcupadas(int sillasOcupadas) {
		SillasOcupadas = sillasOcupadas;
	}

	public Compartimento(String name, int W, int H, int[] contfila, int sillasocupadas){
		
		DispSillas= new int[][] {	{2,2,1},
									{3,6,3},
									{3,6,3},
									{3,6,3},
									{2,6,3},
									{4,8,4},
									{1,2,1},
									{3,6,3},
									{1,6,1}};
		
		Nombre=name;
		SillasSup=0;
		SillasInf=0;
		SillasCentro=0;
		AnchoAvion=W;
		AltoAvion=H;
		ContFila=contfila;
		
		PosYSup=(int)(AltoAvion*0.1111);
		PosYInf=(int)(AltoAvion*0.794);
		PosYCentro1=(int)(AltoAvion*0.405);
		
		switch (Nombre) {
		case "C":
			Fila=0;
			break;
		case "D":
			Fila=1;
			break;
		case "E":
			Fila=2;
			break;
		case "F":
			Fila=3;
			break;
		case "G":
			Fila=4;
			PosYSup=(int)(AltoAvion*0.2063);
			PosYInf=(int)(AltoAvion*0.68);
			break;
		case "H":
			Fila=5;
			PosYSup=(int)(AltoAvion*0.2063);
			PosYInf=(int)(AltoAvion*0.68);
			break;
		case "I":
			Fila=6;
			PosYSup=(int)(AltoAvion*0.2063);
			PosYInf=(int)(AltoAvion*0.68);
			break;
		case "J":
			Fila=7;
			break;
		case "K":
			Fila=8;
			break;
		case "D2":
			Fila=1;
			SillasSup=-1;
			break;
		case "F2":
			Fila=3;
			SillasSup=-3;
			SillasCentro=-4;
			SillasInf=-2;
			break;
		case "G2":
			Fila=4;
			SillasCentro=-2;
			PosYSup=(int)(AltoAvion*0.2063);
			PosYInf=(int)(AltoAvion*0.68);
			break;
		case "H2":
			Fila=5;
			SillasCentro=-2;
			PosYSup=(int)(AltoAvion*0.2063);
			PosYInf=(int)(AltoAvion*0.68);
			break;
		case "I2":
			Fila=6;
			SillasSup=0;
			SillasCentro=-2;
			PosYSup=(int)(AltoAvion*0.2063);
			PosYInf=(int)(AltoAvion*0.68);
			break;

		}
		
		SillasSup+=DispSillas[Fila][0];
		SillasCentro+=DispSillas[Fila][1];
		SillasInf+=DispSillas[Fila][2];
		
		CantSillas=SillasSup+SillasInf+SillasCentro;
		SillasOcupadas=sillasocupadas;
		
		if(CantSillas==SillasOcupadas){
			Ocupado=true;
		}else{
			Ocupado=false;
		}
		
		//mHandler=handler;	
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context=getActivity();
		cambiopasajeros=(CambioPasajeros)context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
				
		View view;
		int CantFilas, PosXAux;
		CantFilas=SillasCentro/2;
		
		
		PosXAux=PosX0[Fila][0];
		for(int a=0; a<SillasSup; a++){
			lpSillas[a].setMargins(PosXAux, PosYSup, 0, 0);
			Sillas[a].setLayoutParams(lpSillas[a]);
			General.addView(Sillas[a]);
			Sillas[a].setNoSilla(ContFila[0], 0);
			ContFila[0]++;
			PosXAux+=AnchoSilla;
		}
		
		PosXAux=PosX0[Fila][1];
		for(int a=0; a<CantFilas; a++){
			lpSillas[a+SillasSup+SillasInf].setMargins(PosXAux, PosYCentro1, 0, 0);
			Sillas[a+SillasSup+SillasInf].setRotation(180);
			Sillas[a+SillasSup+SillasInf].setLayoutParams(lpSillas[a+SillasSup+SillasInf]);
			General.addView(Sillas[a+SillasSup+SillasInf]);	
			Sillas[a+SillasSup+SillasInf].setNoSilla(ContFila[1], 1);
			ContFila[1]++;
			PosXAux+=AnchoSilla;
		}
		PosXAux=PosX0[Fila][1];
		for(int a=0; a<CantFilas; a++){
			lpSillas[a+SillasSup+SillasInf+CantFilas].setMargins(PosXAux, PosYCentro2, 0, 0);
			Sillas[a+SillasSup+SillasInf+CantFilas].setLayoutParams(lpSillas[a+SillasSup+SillasInf+CantFilas]);
			General.addView(Sillas[a+SillasSup+SillasInf+CantFilas]);
			Sillas[a+SillasSup+SillasInf+CantFilas].setNoSilla(ContFila[2], 2);
			ContFila[2]++;
			PosXAux+=AnchoSilla;
		}


		
		switch (Nombre) {
		case "G": case "G2":
			
			int PosYAux=(int)(AltoAvion*0.794);

			PosXAux=PosX0[Fila][2];
			for(int a=0; a<SillasInf; a++){
				
				if(a==0){
					lpSillas[a+SillasSup].setMargins(PosXAux, PosYAux, 0, 0);
				}else{
					lpSillas[a+SillasSup].setMargins(PosXAux, PosYInf, 0, 0);
				}
				Sillas[a+SillasSup].setRotation(180);
				Sillas[a+SillasSup].setLayoutParams(lpSillas[a+SillasSup]);
				General.addView(Sillas[a+SillasSup]);
				Sillas[a+SillasSup].setNoSilla(ContFila[3], 3);
				ContFila[3]++;
				PosXAux+=AnchoSilla;
			}
			
			break;
			
		default:
		
			PosXAux=PosX0[Fila][2];
			for(int a=0; a<SillasInf; a++){
				lpSillas[a+SillasSup].setMargins(PosXAux, PosYInf, 0, 0);
				Sillas[a+SillasSup].setRotation(180);
				Sillas[a+SillasSup].setLayoutParams(lpSillas[a+SillasSup]);
				General.addView(Sillas[a+SillasSup]);
				Sillas[a+SillasSup].setNoSilla(ContFila[3], 3);
				ContFila[3]++;
				PosXAux+=AnchoSilla;
			}
			
			break;
		}
				
		if(Ocupado==false){
			Rellanar();
		}
		
		view=General;
		
			
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		
		
		
		DwSilla=this.getResources().getDrawable(R.drawable.silla1);
		AnchoSilla=(int) (DwSilla.getIntrinsicWidth()*0.92);
		AltoSilla=DwSilla.getIntrinsicHeight();
		
		PosYCentro2=PosYCentro1+AltoSilla;		
		
		PosX0=new int[][]{	{0,										(int)(AnchoAvion*0.0238), 				(int)(AnchoAvion*0.011)},
							{AnchoSilla*2,							AnchoSilla+(int)(AnchoAvion*0.0238),	AnchoSilla+(int)(AnchoAvion*0.011)},
							{AnchoSilla*5,							AnchoSilla*4+(int)(AnchoAvion*0.0238),	AnchoSilla*4+(int)(AnchoAvion*0.011)},
							{AnchoSilla*8,							AnchoSilla*7+(int)(AnchoAvion*0.0238),	AnchoSilla*7+(int)(AnchoAvion*0.011)},
							{AnchoSilla*11+(int)(AnchoAvion*0.011),	AnchoSilla*10+(int)(AnchoAvion*0.0238),	AnchoSilla*10+(int)(AnchoAvion*0.011)},
							{AnchoSilla*13+(int)(AnchoAvion*0.011),	AnchoSilla*13+(int)(AnchoAvion*0.0238),	AnchoSilla*13+(int)(AnchoAvion*0.011)},
							{AnchoSilla*17+(int)(AnchoAvion*0.011),	AnchoSilla*17+(int)(AnchoAvion*0.0238),	AnchoSilla*17+(int)(AnchoAvion*0.011)},
							{AnchoSilla*18+(int)(AnchoAvion*0.011),	AnchoSilla*18+(int)(AnchoAvion*0.0238),	AnchoSilla*18+(int)(AnchoAvion*0.011)},
							{AnchoSilla*21+(int)(AnchoAvion*0.011),	AnchoSilla*21+(int)(AnchoAvion*0.0238),	AnchoSilla*21+(int)(AnchoAvion*0.011)}};
		
	
		General=new FrameLayout(getActivity());
		Sillas=new MySilla[CantSillas];
		lpSillas=new FrameLayout.LayoutParams[CantSillas];
		
	
		for(int a=0; a<CantSillas;a++){
			final int b=a;
			Sillas[a]=new MySilla(getActivity(),Ocupado);
			Sillas[a].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SillasOcupadas=Sillas[b].ToogleEstado(SillasOcupadas);
					cambiopasajeros.ObtenerPasajeros(SillasOcupadas, Fila);
					
				}
			});
					
		
		
			lpSillas[a]=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		}
		
	}
	
//	public int[] getfilas(int Comp){
//		
//		return DispSillas[Comp];
//	}
	
	private void Rellanar(){
		int ContPos, ContSilla, ContF;

		ContF=0;
		for(int a=0; a<Fila; a++){
			ContF+=DispSillas[a][0];
		}
		
		ContPos=0;
		
		switch (Nombre) {
		case "C":
		case "H":
		case "H2":
		case "I":
		case "I2":
		case "J":
		case "K":
			ContPos=0;
			break;
		case "D":
		case "D2":
		case "E":
		case "F":
		case "F2":	
		case "G":
		case "G2":	
			ContPos=1;
			ContF--;
			break;
			

		}
		
			
		for(int a=0; a<SillasOcupadas; a++){
			ContSilla=0;
			if(!(ContF>21 && (ContPos==0||ContPos==3))){
				while(!(Sillas[ContSilla].Nosilla[0]==ContF&&Sillas[ContSilla].Nosilla[1]==ContPos)){
					ContSilla++;
				}
				Sillas[ContSilla].ToogleEstado(SillasOcupadas);
			}else{
				a--;
			}
			ContPos++;
			

			if(ContPos==4){
				ContPos=0;
				ContF++;
			}
			
		}
		
		
		
	}
	
	
}
