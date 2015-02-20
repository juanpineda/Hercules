package com.example.carga;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Seconda extends FragmentActivity implements CambioPasajeros{
	
	int Configuracion;
	int[] idEdit, Pasajeros, Peso;
	Spinner ConfigMision;
	EditText[] Edit;
	DistCarga distcarga;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seconda);
		
		Intent intent; 
		
		intent=getIntent();
		
		
		
		idEdit=new int[]{	R.id.CompC,
							R.id.CompD,
							R.id.CompE,
							R.id.CompF,
							R.id.CompG,
							R.id.CompH,
							R.id.CompI,
							R.id.CompJ,
							R.id.CompK};
		Pasajeros=new int[9];
		Peso=new int[9];
		Configuracion=intent.getIntExtra("Conf", 0);
		Pasajeros=intent.getIntArrayExtra("Pasajeros");
		Peso=intent.getIntArrayExtra("Peso");
		
		Edit= new EditText[9];
//		Pasajeros=new int[]{5,12,12,12,11,16,4,12,8};
//		Peso=new int[]{0,0,0,0,0,0};
		
		for(int a=0; a<9;a++){
			Edit[a]=(EditText) findViewById(idEdit[a]);
		}
		
		
		
		 ConfigMision=(Spinner)findViewById(R.id.Configuracion);
	        List<String> Lista=new ArrayList<String>();
	        Lista.add("Solo Pasajeros (92)");
	        Lista.add("1 Pallet/Pasajeros");
	        Lista.add("2 Pallet/Pasajeros");
	        Lista.add("3 Pallet/Pasajeros");
	        Lista.add("4 Pallet/Pasajeros");
	        Lista.add("5 Pallet/Pasajeros");
	        Lista.add("6 Pallet");
	        Lista.add("64 Paracaidistas");
	        
	        ArrayAdapter<String> Adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Lista);
	        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        
	        ConfigMision.setAdapter(Adapter);
	        
	        ConfigMision.setSelection(Configuracion);
	        
	        ConfigMision.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					int Temp;
					int[] MaxPasajeros;
					switch (arg2) {
					case 0:
					case 1:	
						MaxPasajeros=new int[]{5,12,12,12,11,16,4,12,8};
						break;
					case 2:
						MaxPasajeros=new int[]{5,12,12,12,11,14,2,0,0};
						break;
					case 3:
						MaxPasajeros=new int[]{5,12,12,12,9,0,0,0,0};
						break;
					case 4:
						MaxPasajeros=new int[]{5,12,12,3,0,0,0,0,0};
						break;
					case 5:
						MaxPasajeros=new int[]{5,11,0,0,0,0,0,0,0};
						break;
					case 6:
					default:
						MaxPasajeros=new int[]{0,0,0,0,0,0,0,0,0};
						break;
					case 7:
						MaxPasajeros=new int[]{5,12,12,12,11,16,4,12,8};
						break;
					}
					
					for(int a=0; a<9; a++){
						Temp=Integer.parseInt(Edit[a].getText().toString());
						if(MaxPasajeros[a]<Temp){
							Edit[a].setText(String.valueOf(MaxPasajeros[a]));
						}
					}
					
					 Configuracion=arg2;
					 getPasajeros();
					 distcarga=DistCarga.newInstance(Configuracion,Pasajeros, Peso);
					 android.support.v4.app.FragmentManager fm=getSupportFragmentManager();;
			         android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();	
						
				    	
				    ft.replace(R.id.FragmentDistCarga, distcarga);
					ft.commit();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	        
	        

//			distcarga=DistCarga.newInstance(Configuracion,Pasajeros, Peso);
//		    android.support.v4.app.FragmentManager fm=getSupportFragmentManager();;
//		    android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
//		    ft.replace(R.id.FragmentDistCarga, distcarga);
//			ft.commit();
			
		    for(int a=0; a<9; a++){
		    	
		    	Edit[a].setText(String.valueOf(Pasajeros[a]));
		    }
				
	        
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seconda, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getPasajeros(){
		   for(int a=0; a<9; a++){
	       	Pasajeros[a]=Integer.parseInt(Edit[a].getText().toString());
		   }
	   }

	@Override
	public void ObtenerPasajeros(int NoPasajeros, int Fila) {
		// TODO Auto-generated method stub
		Edit[Fila].setText(String.valueOf(NoPasajeros));
		Pasajeros[Fila]=NoPasajeros;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent; 
			
			intent=getIntent();
			
			intent.putExtra("Conf2", Configuracion);
			intent.putExtra("Pasajeros2", Pasajeros);
			setResult(RESULT_OK, intent);
			finish();
			
	        return true;
	    }
		return super.onKeyDown(keyCode, event);
	}
}
