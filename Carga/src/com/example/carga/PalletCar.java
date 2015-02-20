package com.example.carga;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PalletCar extends Fragment {
	
	TextView NoPallet, Estacion, Peso;
	int NoPeso, idPallet, NoEstacion, PosX, PosY;
	
	
	public static PalletCar newInstance(int peso, int id, int estacion, int posx, int posy){
		
		PalletCar fragment=new PalletCar();
		final Bundle args=new Bundle(5);
		args.putInt("Peso", peso);
		args.putInt("ID", id);
		args.putInt("Estacion", estacion);
		args.putInt("PosX", posx);
		args.putInt("PosY", posy);
		
		fragment.setArguments(args);
		return fragment;
		
	}

  
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		 View view;
		 FrameLayout Rel;
		 FrameLayout.LayoutParams lp= new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		 lp.setMargins(PosX, PosY, 0, 0);
		 view=inflater.inflate(R.layout.palletcar, container,false);
		 //PalletCar1=(RelativeLayout)view.findViewById(R.layout.palletcar);
		 Estacion=(TextView) view.findViewById(R.id.textValorEstacion);
		 NoPallet=(TextView) view.findViewById(R.id.textValorNumeroPallet);
		 Peso=(TextView) view.findViewById(R.id.textValorPeso);
		 Rel=(FrameLayout) view.findViewById(R.id.palletcar);
		 Rel.setLayoutParams(lp);
		 
		 Estacion.setText(String.valueOf(NoEstacion));
		 NoPallet.setText(String.valueOf(idPallet));
	     Peso.setText(String.valueOf(NoPeso));
		 
		 
		return view;
	}
	 
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		
		
		super.onCreate(savedInstanceState);
	
		Bundle args=getArguments();
		
		if(args!=null){
			
			NoPeso=args.getInt("Peso");
			idPallet=args.getInt("ID");
			NoEstacion=args.getInt("Estacion");
			PosX=args.getInt("PosX");
			PosY=args.getInt("PosY");
			
		}
		
	
	}

}
