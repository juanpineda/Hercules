package com.example.carga;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.carga.R;


public class MySilla extends ImageView{

	Boolean Ocupada;
	int[] Nosilla;
	
	public MySilla(Context context, boolean O) {
		super(context);
		  init(context, O);
	}
	
	public MySilla(Context context, AttributeSet attrs, boolean O){
		 super(context, attrs);
		 init(context, O);
		    
	}
	public MySilla(Context context, AttributeSet attrs, int defStyle, boolean O) {
	    super(context, attrs, defStyle);
	    init(context, O);
	}

	private void init(Context context, boolean O) {
		
		if(O==true){
			Ocupada=true;
			setImageResource(R.drawable.silla1);
			
		}else{
			Ocupada=false;
			setImageResource(R.drawable.silla2);
		}
		Nosilla=new int[2];
		
		
	}
	
	public void setNoSilla(int Fila, int Pos){
		
		Nosilla[0]=Fila;
		Nosilla[1]=Pos;
		
	}
	
	
	public int ToogleEstado(int Contador){
	
		if(Ocupada==false){
			Ocupada=true;
			setImageResource(R.drawable.silla1);
			Contador++;
		}else{
			Ocupada=false;
			setImageResource(R.drawable.silla2);
			Contador--;
			
		}
		return Contador;
	}
}
