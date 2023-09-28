package com.example.nahuasupp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.sourceforge.jtds.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtCorreo;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre=(EditText)findViewById(R.id.edtNombres) ;
        edtCorreo=(EditText)findViewById(R.id.edtCorreo) ;
        btnAgregar=(Button)findViewById(R.id.btnAgregarUsuario) ;

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                agregarUsuario();
            }

        });
    }


    public Connection conexionBD()
    {
        Connection conexion=null;
        try {
            StrictMode.ThreadPolicy police= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(police);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.22;databasename=DesarrolloAndroid;user=usuario;password=1234567890");

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public void agregarUsuario ()
    {
        try {
            PreparedStatement pst=conexionBD().prepareStatement("insert into Usuarios values (?,?)");
            pst.setString(1,edtNombre.getText().toString());
            pst.setString(2,edtCorreo.getText().toString());
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"REGISTRO AGREGADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}