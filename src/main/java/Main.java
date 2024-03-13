import Connections.MySQL.GardenElementsMySQL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main (String[] args){
       /*GardenElementsMySQL gardenElementsMySQL = new GardenElementsMySQL<>();
        HashMap<Integer, String> listaFlowers = new HashMap<>();
        try {
            listaFlowers = gardenElementsMySQL.showFlowerStore();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Set<Integer>listaId = listaFlowers.keySet();
        for(Integer id : listaId){
            String value = listaFlowers.get(id);
            System.out.println("id: " + id + " nombre: " + value);
        }*/
       //App.runProgram();
        App.runApp();
        App.runProgram();

        //App nue = new App();
        //nue.runApp();
        //App.runApp();

    }
}

