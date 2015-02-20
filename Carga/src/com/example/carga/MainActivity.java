package com.example.carga;



import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends FragmentActivity  implements CambioPasajeros{
	

DistCarga distcarga;
PalletCar palletcar;
Spinner ConfigMision;
EditText[] Edit;
int[] idText, Pasajeros, Peso;
int Configuracion;

int Estonosirve;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        Configuracion=0;
        Peso= new int[]{1000,2000,3000,4000,5000,6000};       
        idText=new int [] {	R.id.textC,
			        		R.id.textD,
			        		R.id.textE,
			        		R.id.textF,
			        		R.id.textG,
			        		R.id.textH,
			        		R.id.textI,
			        		R.id.textJ,
			        		R.id.textK};
        Pasajeros=new int[9];
        Edit=new EditText[9];     
        for(int a=0; a<9; a++){
        	Edit[a]=(EditText)findViewById(idText[a]);
        	Pasajeros[a]=Integer.parseInt(Edit[a].getText().toString());
        }     
        ConfigMision=(Spinner)findViewById(R.id.spinner1);
        List<String> Lista=new ArrayList<String>();
        Lista.add("Solo Pasajeros (92)");
        Lista.add("1 Pallet/Pasajeros");
        Lista.add("2 Pallet/Pasajeros");
        Lista.add("3 Pallet/Pasajeros");
        Lista.add("4 Pallet/Pasajeros");
        Lista.add("5 Pallet/Pasajeros");
        Lista.add("6 Pallet");
        Lista.add("64 Paracaidistas");      
        ArrayAdapter<String> Adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Lista);
        Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     
        ConfigMision.setAdapter(Adaptador);       
        ConfigMision.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
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
				 distcarga=DistCarga.newInstance(arg2,Pasajeros, Peso);
			     android.support.v4.app.FragmentManager fm=getSupportFragmentManager();;
			     android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();		    	
			    ft.replace(R.id.layoutdistcarga, distcarga);
				ft.commit();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
        distcarga=DistCarga.newInstance(0,Pasajeros, Peso);
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();;
    	android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
    	ft.replace(R.id.layoutdistcarga, distcarga);
		ft.commit();     
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent intent = new Intent(this, Seconda.class);
        	intent.putExtra("Conf", Configuracion);
        	intent.putExtra("Pasajeros", Pasajeros);
        	intent.putExtra("Peso", Peso);
        	startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }    
    public void Cambio(View view){
    	getPasajeros();
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();;
    	android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();	
    	ft.add(R.id.layoutdistcarga, palletcar);
    	ft.commit();		
   }  
   private void getPasajeros(){
	   for(int a=0; a<9; a++){
       	Pasajeros[a]=Integer.parseInt(Edit[a].getText().toString());
	   }
   }
	@Override
	public void ObtenerPasajeros(int NoPasajeros, int Fila) {
		Edit[Fila].setText(String.valueOf(NoPasajeros));
		Pasajeros[Fila]=NoPasajeros;		
	}		
	@Override
		protected void onActivityResult(int arg0, int arg1, Intent arg2) {
			super.onActivityResult(arg0, arg1, arg2);			
			if(arg1==RESULT_OK){				
				Configuracion=arg2.getExtras().getInt("Conf2");
				Pasajeros=arg2.getExtras().getIntArray("Pasajeros2");
				ConfigMision.setSelection(Configuracion);				
				for(int a=0; a<9; a++){
					Edit[a].setText(String.valueOf(Pasajeros[a]));
				}								
			}
		}
}
