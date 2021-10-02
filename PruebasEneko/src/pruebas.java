import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class pruebas {

    public void paths() {
        String currentPath = System.getProperty("user.dir");
        String addDirPath = currentPath + File.separator + "src" + File.separator;
        System.out.println(addDirPath);
    }

    public void launchBashProcess(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe","/c","dir C:\\Users\\link");
        try {
            /*
            creamos un proceso y le metemos el processBuilder con el que
            usamos el comando que queremos usar en el bash,
            creamos un StringBuilder al que le meteremos la respuesta al
            comando mediante un BufferedReader que se creara a base de el
            input Stream del proceso
             */
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line=reader.readLine())!=null){
                output.append(line +"\n");
            }

            int exitVal=process.waitFor();
            if(exitVal==0){
                System.out.println("No hubo problemas");
                System.out.println(output);
                System.exit(0);
            }else{
                System.out.println("sucedio un error");
            }


        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void launchRunTimeProcess(){
        /*
        lo mismo de arriba solo que lo ejecuta en el runtime actual
        en lugar de ejecutarlo y luego leerlo, lo ejecuta y lee al mismo tiempo
         */
        try {
            Process process = Runtime.getRuntime().exec("ls /User/linkhjs");
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line=reader.readLine())!=null){
                output.append(line+"\n");
            }

            int exitVal = process.exitValue();
            if (exitVal==0){
                System.out.println("done");
                System.out.println(output);
                System.exit(0);
            }
            else{
                System.out.println("ha habido un error");
                System.out.printf("al buscar un directorio salio con %d",exitVal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        pruebas p = new pruebas();
        //p.paths();
        //p.launchBashProcess();
    }
}
