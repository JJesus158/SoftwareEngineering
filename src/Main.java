import Enum.ContactoPredefinido;
import Enum.TipoSocio;
import Gestores.GestorConfiguracoes;
import Gestores.GestorLivros;
import Gestores.GestorRequisicoes;
import Gestores.GestorSocios;
import Modelos.Socio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {

        try (FileInputStream fileIn = new FileInputStream("gestor_livros.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GestorLivros.setInstance((GestorLivros) in.readObject());
        } catch (IOException i) {
            GestorLivros.getInstance();
        } catch (ClassNotFoundException c) {
            System.out.println("GestorLivros class not found");
            c.printStackTrace();
            GestorLivros.getInstance();
        }

        // carrega as configuracoes salvadas
        try (FileInputStream fileIn = new FileInputStream("configuracoes.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GestorConfiguracoes.setInstance((GestorConfiguracoes) in.readObject());
        } catch (IOException i) {
            GestorConfiguracoes.getInstance();
        } catch (ClassNotFoundException c) {
            System.out.println("GestorConfiguracoes class not found");
            c.printStackTrace();
            GestorConfiguracoes.getInstance();
        }

        // carrega o gestor de socios salvado
        try (FileInputStream fileIn = new FileInputStream("gestor_socios.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GestorSocios.setInstance((GestorSocios) in.readObject());
        } catch (IOException i) {
            GestorSocios.getInstance();
        } catch (ClassNotFoundException c) {
            System.out.println("GestorSocios class not found");
            c.printStackTrace();
            GestorSocios.getInstance();
        }

        // carrega o gestor de requisicoes salvado
        try (FileInputStream fileIn = new FileInputStream("gestor_requisicoes.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GestorRequisicoes.setInstance((GestorRequisicoes) in.readObject());
        } catch (IOException i) {
            GestorRequisicoes.getInstance();
        } catch (ClassNotFoundException c) {
            System.out.println("GestorRequisicoes class not found");
            c.printStackTrace();
            GestorRequisicoes.getInstance();
        }

        new EcraPrincipal();
    }


}