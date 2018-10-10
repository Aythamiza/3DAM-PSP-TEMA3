import java.io.* ;

import java.net.* ;
import java.util.Random;

class Servidor {

    static final int Puerto=2000;

    public Servidor( ) {

        try {

            ServerSocket skServidor = new ServerSocket(Puerto);

            System.out.println("Escucho el puerto " + Puerto );

            Socket skcliente = skServidor.accept();
            System.out.println("cliente conectado");

            //flujos de entrada y salida

            InputStream is = skcliente.getInputStream();
            DataInputStream flujo_entrada= new DataInputStream(is);

            OutputStream os = skcliente.getOutputStream();
            DataOutputStream flujo_salida= new DataOutputStream( os);

            Random randomGenerator = new Random();
            int num_secreto=randomGenerator.nextInt(100);
            int encontrado=0;
            String num_cliente = new String();
            flujo_salida.writeUTF("Tienes que adivinar un numero del 1 al 100");

            while(encontrado==0){
                num_cliente=flujo_entrada.readUTF();
                System.out.println("\tEl cliente ha dicho " +num_cliente);
                if (num_secreto==Integer.parseInt(num_cliente)){
                    flujo_salida.writeUTF("Correcto");
                    encontrado=1;

                }else{
                  if (num_secreto>Integer.parseInt(num_cliente)){
                   flujo_salida.writeUTF("El numero secreto es mayor");

                  } else
                      flujo_salida.writeUTF("El numero secreto es menor");


                }
            }

            flujo_salida.writeUTF("FIN");
            skcliente.close();
            System.out.println("Cliente desconectado");



        } catch( Exception e ) {

            System.out.println( e.getMessage() );

        }

    }

    public static void main( String[] arg ) {

        new Servidor();

    }

}


