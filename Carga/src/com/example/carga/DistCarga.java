package com.example.carga;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class DistCarga extends Fragment {
	
	int[] idPallets, MaxFilas, Pasajeros, Peso;
	int AvionWidth, AvionHeight,PosXFL, NoPallet, NoCompartimentos;
	MyPallet[] Pallet;
	FrameLayout Pallets; 
	FrameLayout TotalLayout1, FL;
	Drawable DwAvion, DwPallet;
	Compartimento[] Sillas;
	String[] idCompartimeto;
	PalletCar palletcar;

	
	public static DistCarga newInstance(int Conf, int[] pasajeros, int[] peso){
		
		DistCarga fragment=new DistCarga();
		final Bundle args=new Bundle(3);
		args.putInt("Conf", Conf);
		args.putIntArray("Pasajeros", pasajeros);
		args.putIntArray("Peso", peso);
		fragment.setArguments(args);
		return fragment;
		
	}
	
//	public DistCarga(){
//		
//		Parametrizar(0);
//		
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view;
		int PosYPallet, PosXPallet;
		FrameLayout.LayoutParams[] lpPallet;
		FrameLayout.LayoutParams lpSillas;
		
		lpPallet=new FrameLayout.LayoutParams[NoPallet];
		
		view=inflater.inflate(R.layout.distcarga, container,false);
		TotalLayout1=(FrameLayout)	view.findViewById(R.id.TotalLayout);
		
		PosYPallet=(int) (AvionHeight*0.2619);
		PosXPallet=(int)(AvionWidth*0.8787);
		
		for(int a=5;  a>5-NoPallet; a--){

			lpPallet[5-a]=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
			lpPallet[5-a].setMargins(PosXPallet, PosYPallet, 0, 0);
			Pallet[5-a].setLayoutParams(lpPallet[5-a]);
			TotalLayout1.addView(Pallet[5-a]);
			Pallet[5-a].ID=a+1;
			Pallet[5-a].Estacion=0;
			Pallet[5-a].PosY=PosYPallet;
			Pallet[5-a].PosX=PosXPallet-150;
			if(a==5){

				PosXPallet=(int)(AvionWidth*0.7554);
				
			}else{
				PosXPallet-=(int)(AvionWidth*0.1125);
				
			}
		}
		
		
		lpSillas=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		lpSillas.setMargins(PosXFL, 0, 0, 0);
		
		for(int a=0; a<NoCompartimentos; a++){
        	
			getActivity().getSupportFragmentManager().beginTransaction().add(R.id.FL, Sillas[a]).commit();

		}
		

		
		FL.setLayoutParams(lpSillas);

		
		TotalLayout1.addView(FL);
		return view;
	}
	
	@SuppressLint("ClickableViewAccessibility") @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		int Configuracion=0;
		
		MaxFilas=new int[]{0,0,0,0};
		Pasajeros=new int[]{0,0,0,0,0,0,0,0,0};
		
		Bundle args=getArguments();
		
		if(args!=null){
			
			Configuracion=getArguments().getInt("Conf",0);
			Pasajeros=getArguments().getIntArray("Pasajeros");
			Peso=getArguments().getIntArray("Peso");
			Parametrizar(Configuracion);
		
		}
		
		
		
		DwAvion=this.getResources().getDrawable(R.drawable.avion);
		DwPallet=this.getResources().getDrawable(R.drawable.seis);
		AvionWidth=DwAvion.getIntrinsicWidth();
		AvionHeight=DwAvion.getIntrinsicHeight();
		
		PosXFL=(int) (AvionWidth*0.2619);
		FL=new FrameLayout(getActivity());
		FL.setId(R.id.FL);
		
		Pallet=new MyPallet[NoPallet];
		for(int a=0; a<NoPallet; a++){
			final int b=a;
			Pallet[a]=new MyPallet(getActivity(),Peso[a],0,0,0,0);
			Pallet[a].setImageResource(idPallets[a]);
			
			Pallet[a].setOnTouchListener(new OnTouchListener() {
				
				@SuppressLint("ClickableViewAccessibility") @Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						palletcar=PalletCar.newInstance(Pallet[b].Peso, Pallet[b].ID, Pallet[b].Estacion, Pallet[b].PosX, Pallet[b].PosY);
						getActivity().getSupportFragmentManager().beginTransaction().add(R.id.FL, palletcar).commit();
						Pallet[b].setColorFilter(Color.YELLOW, Mode.OVERLAY);
						
						break;
					case MotionEvent.ACTION_UP:
					
						getActivity().getSupportFragmentManager().beginTransaction().remove(palletcar).commit();
						Pallet[b].setColorFilter(Color.TRANSPARENT);
						break;
						
					default:
						break;
					}
					
					return true;
				}
			});
		}
		
		
		Sillas=new Compartimento[NoCompartimentos];
		for(int a=0; a<NoCompartimentos; a++){
			Sillas[a]= new Compartimento(idCompartimeto[a], AvionWidth, AvionHeight,MaxFilas,Pasajeros[a]);
		}
		
		
		
	}

	private void Parametrizar(int Conf){
	
		switch (Conf) {
		case 0: //Solo Pasajeros
		case 7: //Parcaidistas
			NoPallet=0;
			NoCompartimentos=9;
			idCompartimeto= new String[]{"C","D","E","F","G","H","I","J","K"};
			idPallets=new int[]{0};
			break;
		case 1: //1 Pallet / 92 Pasajeros
			NoPallet=1;
			NoCompartimentos=9;
			idCompartimeto= new String[]{"C","D","E","F","G","H","I","J","K"};
			idPallets=new int[]{R.drawable.seis};
			break;
		case 2: //2 Pallet / 68 Pasajeros
			NoPallet=2;
			NoCompartimentos=7;
			idCompartimeto= new String[]{"C","D","E","F","G","H2","I2"};
			idPallets=new int[]{R.drawable.seis,
								R.drawable.cinco};
			break;
		case 3: //3 Pallet / 50 Pasajeros
			NoPallet=3;
			NoCompartimentos=5;
			idCompartimeto= new String[]{"C","D","E","F","G2"};
			idPallets=new int[]{R.drawable.seis,
								R.drawable.cinco,
								R.drawable.cuatro};
			break;
		case 4: //4 Pallet / 32 Pasajeros
			NoPallet=4;
			NoCompartimentos=4;
			idCompartimeto= new String[]{"C","D","E","F2"};
			idPallets=new int[]{R.drawable.seis,
								R.drawable.cinco,
								R.drawable.cuatro,
								R.drawable.tres};
			break;
		case 5: //5 Pallet / 16 Pasajeros
			NoPallet=5;
			NoCompartimentos=2;
			idCompartimeto= new String[]{"C","D2"};
			idPallets=new int[]{R.drawable.seis,
								R.drawable.cinco,
								R.drawable.cuatro,
								R.drawable.tres,
								R.drawable.dos};
			break;
		case 6: //6 Pallet 
			NoPallet=6;
			NoCompartimentos=0;
			idCompartimeto= new String[]{""};
			idPallets=new int[]{R.drawable.seis,
								R.drawable.cinco,
								R.drawable.cuatro,
								R.drawable.tres,
								R.drawable.dos,
								R.drawable.uno};
			break;

		}
	}

}

