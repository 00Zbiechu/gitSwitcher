package changebranch;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;

@Slf4j
public class View extends JFrame {

    @Getter
    private final JTextField insertYourPath;
    @Getter
    private final JButton confirmYourPathButton;
    @Getter
    private final JPanel branchPanel;
    @Getter
    private final JTextArea areaForStatus;
    @Getter
    private final JButton checkStatusButton;
    @Getter
    private final JButton buttonAddChanges;
    @Getter
    private final JTextField messageForCommit;
    @Getter
    private final JButton confirmCommit;
    @Getter
    private final JButton doAmend;
    @Getter
    private final JButton doPushButton;



    public View() {

        // Kod wygenerowany przy pomocy WindowBuilder

        setVisible(true);
        setTitle("gitSwitcher");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 401, 527);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel pathToRepoPanel = new JPanel();
        pathToRepoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));

        JLabel insertYourPathLabel = new JLabel("Wprowadź ścieżkę do lokalnego repozytorium");
        insertYourPathLabel.setHorizontalAlignment(SwingConstants.CENTER);

        insertYourPath = new JTextField();
        insertYourPath.setToolTipText("Ścieżka do lokalnego repozytorium...");
        insertYourPath.setHorizontalAlignment(SwingConstants.LEFT);
        insertYourPath.setColumns(35);


        confirmYourPathButton = new JButton("Zatwierdź ścieżkę");
        GroupLayout gl_pathToRepoPanel = new GroupLayout(pathToRepoPanel);
        gl_pathToRepoPanel.setHorizontalGroup(
                gl_pathToRepoPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pathToRepoPanel.createSequentialGroup()
                                .addGroup(gl_pathToRepoPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_pathToRepoPanel.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(insertYourPathLabel, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_pathToRepoPanel.createSequentialGroup()
                                                .addGap(24)
                                                .addComponent(insertYourPath, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                                        .addGroup(gl_pathToRepoPanel.createSequentialGroup()
                                                .addGap(99)
                                                .addComponent(confirmYourPathButton)))
                                .addContainerGap())
        );
        gl_pathToRepoPanel.setVerticalGroup(
                gl_pathToRepoPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pathToRepoPanel.createSequentialGroup()
                                .addComponent(insertYourPathLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(insertYourPath, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(confirmYourPathButton)
                                .addGap(24))
        );
        pathToRepoPanel.setLayout(gl_pathToRepoPanel);


        JPanel containerForBranchAndStatus = new JPanel();
        containerForBranchAndStatus.setLayout(new GridLayout(0, 2, 0, 0));


        branchPanel = new JPanel();
        branchPanel.setBorder(null);
        containerForBranchAndStatus.add(branchPanel);

        JLabel yourBranchMessage = new JLabel("Twoje branch'e:");
        branchPanel.add(yourBranchMessage);
        branchPanel.setLayout(new GridLayout(5,1));

        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        containerForBranchAndStatus.add(statusPanel);

        areaForStatus = new JTextArea();
        areaForStatus.setEnabled(false);
        areaForStatus.setEditable(false);
        areaForStatus.setRows(5);
        areaForStatus.setColumns(16);
        areaForStatus.setLineWrap(false);

//      Skrolowanie dla areaForStatus
        JScrollPane scroll = new JScrollPane (areaForStatus,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        checkStatusButton = new JButton("Sprawdź status");
        checkStatusButton.setEnabled(false);
        GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
        gl_statusPanel.setHorizontalGroup(
                gl_statusPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_statusPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_statusPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_statusPanel.createSequentialGroup()
                                                .addGap(12)
                                                .addComponent(checkStatusButton))
                                        .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_statusPanel.setVerticalGroup(
                gl_statusPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_statusPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(checkStatusButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statusPanel.setLayout(gl_statusPanel);

        JPanel containerForAddAndCommit = new JPanel();
        containerForAddAndCommit.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        containerForAddAndCommit.setLayout(new BoxLayout(containerForAddAndCommit, BoxLayout.X_AXIS));

        JPanel addChangesPanel = new JPanel();
        addChangesPanel.setBorder(null);
        containerForAddAndCommit.add(addChangesPanel);

        JLabel addChanges = new JLabel("Dodaj zmiany");
        addChanges.setHorizontalAlignment(SwingConstants.CENTER);

        buttonAddChanges = new JButton("Dodaj");
        buttonAddChanges.setEnabled(false);
        GroupLayout gl_addChangesPanel = new GroupLayout(addChangesPanel);
        gl_addChangesPanel.setHorizontalGroup(
                gl_addChangesPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_addChangesPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_addChangesPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_addChangesPanel.createSequentialGroup()
                                                .addGap(12)
                                                .addComponent(buttonAddChanges))
                                        .addComponent(addChanges))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        gl_addChangesPanel.setVerticalGroup(
                gl_addChangesPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_addChangesPanel.createSequentialGroup()
                                .addGap(47)
                                .addComponent(addChanges)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(buttonAddChanges)
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        addChangesPanel.setLayout(gl_addChangesPanel);

        JPanel panel = new JPanel();
        panel.setBorder(null);
        containerForAddAndCommit.add(panel);

        JLabel commitMessage = new JLabel("Nazwa commit'a");

        messageForCommit = new JTextField();
        messageForCommit.setEnabled(false);
        messageForCommit.setColumns(10);

        confirmCommit = new JButton("Zatwierdź");
        confirmCommit.setEnabled(false);

        doAmend = new JButton("Zastąp");
        doAmend.setEnabled(false);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(messageForCommit, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(13, Short.MAX_VALUE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(commitMessage)
                                                .addGap(61))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(confirmCommit)
                                                .addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                .addComponent(doAmend)
                                                .addContainerGap())))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(commitMessage)
                                .addGap(4)
                                .addComponent(messageForCommit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(confirmCommit)
                                        .addComponent(doAmend))
                                .addContainerGap())
        );
        panel.setLayout(gl_panel);

        JPanel panel_1 = new JPanel();


        doPushButton = new JButton("Wykonaj push do Github");
        doPushButton.setEnabled(false);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(containerForBranchAndStatus, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(containerForAddAndCommit, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                                .addComponent(pathToRepoPanel, 0, 0, Short.MAX_VALUE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(338)
                                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(85)
                                                .addComponent(doPushButton)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(1)
                                .addComponent(pathToRepoPanel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(containerForBranchAndStatus, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(containerForAddAndCommit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(doPushButton)
                                                .addContainerGap())))
        );
        contentPane.setLayout(gl_contentPane);

        log.info("Wykonano konstruktor klasy View");



    }

    //Dodanie eventListenera do obiektów w ZmianaBranchy.View, które będą obsługiwane w klasie ZmianaBranchy.Controller
    public void addViewListener(ActionListener actionListener){

        checkStatusButton.addActionListener(actionListener);
        buttonAddChanges.addActionListener(actionListener);
        confirmCommit.addActionListener(actionListener);
        confirmYourPathButton.addActionListener(actionListener);
        doAmend.addActionListener(actionListener);
        doPushButton.addActionListener(actionListener);

        log.info("Dodanie action listenera do pól w klasie View");

    }



}
