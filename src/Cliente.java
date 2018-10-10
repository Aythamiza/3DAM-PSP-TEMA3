import java.io.*;

import java.net.*;

class Cliente {

    static final String HOST = "localhost";

    static final int Puerto=2000;


    public Cliente( ) {

        String datos= new String();
        String num_cliente= new String();
        // leer el teclado

        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        try{

            Socket sCliente = new Socket( HOST , Puerto );

            InputStream aux = sCliente.getInputStream();
            DataInputStream flujo_entrada = new DataInputStream( aux );

            OutputStream os= sCliente.getOutputStream();
            DataOutputStream flujo_salida= new DataOutputStream(os);

            System.out.println( flujo_entrada.readUTF() );


        datos =flujo_entrada.readUTF();
        System.out.println(datos);

        do{

         System.out.println("introduce un numero");
         num_cliente=reader.readLine();
         flujo_salida.writeUTF(num_cliente);
        }while(!datos.equals("Correcto"));


            sCliente.close();

      } catch( Exception e ) {

        System.out.println( e.getMessage() );


    }



    }

    public static void main( String[] arg ) {

        new Cliente();

    }

}

