import Database.Dao.ExceptionDAO;
import modulo.servidores.View.BancoCadView;
import modulo.servidores.View.EmployeeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuForm extends JFrame {

    public MenuForm() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        this.configuraMenu();
    }

    private void initComponents() {
        // Exibe mensagem ao sair
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                formWindowClosing();
            }
        });
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        /*
         *  MENU APLICAÇÃO
         */
        JMenu fileMenu = new JMenu("Aplicação");
        JMenuItem exitAction = new JMenuItem("Sair");
        exitAction.addActionListener((ActionEvent e) -> {
            sairDoApp();
        });
        fileMenu.add(exitAction);

        /*
         *  MENU CADASTROS
         */
        JMenu cadastroMenu = new JMenu("Cadastros");
        JMenuItem cadastroEstudantes = new JMenuItem("Cadastro de Estudantes");
        cadastroEstudantes.addActionListener((ActionEvent e) -> {
            cadastraEstudantesAction();
        });
//        JMenu funcionarios = new JMenu("Funcionários");

        JMenuItem cadastroFuncionarios = new JMenuItem("Cadastro de Funcionários");
        cadastroFuncionarios.addActionListener((ActionEvent e) -> {
            try {
                cadastroServidoresAction();
            } catch (ExceptionDAO ex) {
                throw new RuntimeException(ex);
            }
        });
//        JMenuItem cadastroContas = new JMenuItem("Cadastro de Contas Bancárias");
//        cadastroContas.addActionListener((ActionEvent e) -> {
//
//            try {
//                cadastroContasAction();
//            } catch (ExceptionDAO ex) {
//                throw new RuntimeException(ex);
//            }
//
//        });

//        funcionarios.add(cadastroFuncionarios);
//        funcionarios.add(cadastroContas);


        cadastroMenu.add(cadastroEstudantes);
        cadastroMenu.add(cadastroFuncionarios);

        menuBar.add(fileMenu);
        menuBar.add(cadastroMenu);
    }

    private void formWindowClosing() {
        this.sairDoApp();
    }

    private void cadastraEstudantesAction() {

    }
    private void cadastroServidoresAction() throws ExceptionDAO {
        new EmployeeView(getInstance(), true, "Cadastro de Funcionários").start();
    }

    private void cadastroContasAction() throws ExceptionDAO {
        new BancoCadView(getInstance(), true, "Cadastro de Contas").start();
    }
    private void sairDoApp() {
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void configuraMenu() {

    }

    public JFrame getInstance() {
        return this;
    }

    public void start() {
        setVisible(true);
    }
}
