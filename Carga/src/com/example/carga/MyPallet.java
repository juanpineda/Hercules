package com.example.carga;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyPallet extends ImageView {
	
	int Peso, ID, Estacion, PosX, PosY;
		

	public MyPallet(Context context, int peso, int id, int estacion, int posx, int posy) {
		super(context);
		// TODO Auto-generated constructor stub
		init(peso, id, estacion, posx, posy);
	}
	public MyPallet(Context context, AttributeSet attrs, int peso, int id, int estacion, int posx, int posy) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(peso, id, estacion, posx, posy);
	}

	public MyPallet(Context context, AttributeSet attrs, int defStyle, int peso, int id, int estacion, int posx, int posy) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(peso, id, estacion, posx, posy);
	}
	
	private void init(int peso, int id, int estacion, int posx, int posy){
		
		Peso=peso;
		ID=id;
		Estacion=estacion;
		PosX=posx;
		PosY=posy;
		
	}
	
	

}
