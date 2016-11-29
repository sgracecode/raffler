import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class RaffleGUI 
{
    RaffleDrawing raffle;
    //Only for use in a specific screen, do NOT use after raffle has been initialized
    RaffleSettings raffleSettings;
    JFrame frame;
    ArrayList<Component> buttonsAndThings;
    Font defont;
    
    public RaffleGUI()
    {
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Raffle Drawing");
        frame.setSize(450, 575);
        frame.setVisible(true);
        
        frame.setLocationRelativeTo(null);
        
        defont = new Font("Arial", Font.PLAIN, 16);
        frame.setFont(defont);
        
        buttonsAndThings = new ArrayList<Component>();
        
        introduction();
        
    }

    private void introduction()
    {
        FlowLayout introLayout = new FlowLayout();
        frame.setLayout(introLayout);
        
        JTextArea explanation = new JTextArea();
        explanation.setBounds(10, 10, frame.getWidth() - 50, frame.getHeight() - 50);
        explanation.setText(
                "Welcome to the second version of Gracie's raffle drawing program!\n\n" +
                "Sorry that it's clunky and ugly, but it's been tested with live data " +
                "so it should hopefully make the raffle process less error prone. " +
                "Right now, the file to read must be a .csv with a column for player names " +
                "and a column for the number of tickets bought in that bundle. Player IDs can " +
                "also be included. There should be no header rows and no extra rows underneath.\n\n" +
                "The files for input and output should be in the same folder as this .jar program. " +
                "The winning ticket numbers are generated randomly, and for reproducibility, " +
                "the generator should be seeded with a truly random number from random.org " + 
                "between 0 and 999999999. Save that seed number for yourself. " +
                "If you input that exact seed again, the same ticket numbers " +
                "will be drawn, so results are repeatable if lost or under suspicion.\n\n" +
                "See the readme in a GDoc file for detailed info on seeding and errors.\n\n" +
                "Please contact @Gracie88 #51032 on FR for concerns or questions!"
                );
        explanation.setFont(defont);
        explanation.setLineWrap(true);
        explanation.setWrapStyleWord(true);
        explanation.setVisible(true);
        frame.add(explanation);
        buttonsAndThings.add(explanation);
        
        Button okay = new Button("Okay");
        okay.setFont(defont);
        frame.add(okay);
        buttonsAndThings.add(okay);
        
        frame.repaint();
        
        okay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                while(!buttonsAndThings.isEmpty())
                {
                    frame.remove(buttonsAndThings.get(0));
                    buttonsAndThings.remove(0);
                }
                dataEntry();
            }
        });
    }
    
    //Putting these variables outside the method due to java 7 compliance attempts
    JTextField batchSize;
    JTextField rows;
    JTextField seed;
    JTextField filename;
    
    private void dataEntry()
    {
        //To set up for the parsing
        raffleSettings = new RaffleSettings();
        
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(15, 0, 0, 0);
        
        //Row 1 all Columns - Top explanation
        int gridY = 0;
        JLabel entryLabel = new JLabel("The file to parse should be a .csv in this directory.");
        entryLabel.setFont(defont);
        entryLabel.setVisible(true);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.VERTICAL;
        frame.add(entryLabel, constraints);
        buttonsAndThings.add(entryLabel);
        
        //Row 2 - Parsing options and relevant information
        gridY++;
        constraints.fill = GridBagConstraints.BOTH;
        //Col 1
        JLabel filenameLabel = new JLabel("Filename: ", 10);
        filenameLabel.setFont(defont);
        filenameLabel.setVisible(true);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(filenameLabel, constraints);
        buttonsAndThings.add(filenameLabel);
        //Col 2
        filename = new JTextField("raffle.csv", 10);
        filename.setFont(defont);
        constraints.gridx = 1;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(filename, constraints);
        buttonsAndThings.add(filename);
        //Col 3
        JLabel seedLabel = new JLabel("Seed: ");
        seedLabel.setFont(defont);
        seedLabel.setVisible(true);
        constraints.gridx = 2;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(seedLabel, constraints);
        buttonsAndThings.add(seedLabel);
        //Col 4
        seed = new JTextField("#", 2);
        seed.setFont(defont);
        constraints.gridx = 3;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(seed, constraints);
        buttonsAndThings.add(seed);
        
        //Row 3 all Columns - Label about column numbers
        gridY++;
        JLabel fileLayoutLabel = new JLabel("Which columns contain the data?");
        fileLayoutLabel.setFont(defont);
        fileLayoutLabel.setVisible(true);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.VERTICAL;
        frame.add(fileLayoutLabel, constraints);
        buttonsAndThings.add(fileLayoutLabel);
        
        //Row 4 - Selecting options for column locations
                                                //None,  0,   1,   2,   3,   4,   5,   6
        String[] comboBoxOptions = new String[] {"N/A", "A", "B", "C", "D", "E", "F", "G"};
        String[] comboBoxMandatoryOptions = new String[] {"A", "B", "C", "D", "E", "F", "G"};

        gridY++;
        constraints.fill = GridBagConstraints.BOTH;
        //Col 1
        JLabel playerIDLabel = new JLabel("Player ID#: ");
        playerIDLabel.setFont(defont);
        playerIDLabel.setVisible(true);
        constraints.gridx = 2;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(playerIDLabel, constraints);
        buttonsAndThings.add(playerIDLabel);
        //Col 2
        JComboBox<String> playerIDBox = new JComboBox<String>(comboBoxOptions);
        playerIDBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JComboBox<String> selectedBox = (JComboBox<String>)event.getSource();
                String choice = (String)selectedBox.getSelectedItem();
                switch(choice)
                {
                    case "G": raffleSettings.idRow = 6; raffleSettings.usePlayerID = true; break;
                    case "F": raffleSettings.idRow = 5; raffleSettings.usePlayerID = true; break;
                    case "E": raffleSettings.idRow = 4; raffleSettings.usePlayerID = true; break;
                    case "D": raffleSettings.idRow = 3; raffleSettings.usePlayerID = true; break;
                    case "C": raffleSettings.idRow = 2; raffleSettings.usePlayerID = true; break;
                    case "B": raffleSettings.idRow = 1; raffleSettings.usePlayerID = true; break;
                    case "A": raffleSettings.idRow = 0; raffleSettings.usePlayerID = true; break;
                    default: raffleSettings.usePlayerID = false;
                }
            }
        });
        playerIDBox.setFont(defont);
        playerIDBox.setVisible(true);
        constraints.gridx = 3;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(playerIDBox, constraints);
        buttonsAndThings.add(playerIDBox);
        
        //Row 5
        gridY++;
        //Col 1
        JLabel playerNameLabel = new JLabel("Username: ");
        playerNameLabel.setFont(defont);
        playerNameLabel.setVisible(true);
        constraints.gridx = 2;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(playerNameLabel, constraints);
        buttonsAndThings.add(playerNameLabel);
        //Col 2
        JComboBox<String> playerNameBox = new JComboBox<String>(comboBoxMandatoryOptions);
        playerNameBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JComboBox<String> selectedBox = (JComboBox<String>)event.getSource();
                String choice = (String)selectedBox.getSelectedItem();
                switch(choice)
                {
                    case "G": raffleSettings.nameRow = 6; break;
                    case "F": raffleSettings.nameRow = 5; break;
                    case "E": raffleSettings.nameRow = 4; break;
                    case "D": raffleSettings.nameRow = 3; break;
                    case "C": raffleSettings.nameRow = 2; break;
                    case "B": raffleSettings.nameRow = 1; break;
                    case "A": raffleSettings.nameRow = 0; break;
                    default: break;
                }
            }
        });
        playerNameBox.setFont(defont);
        playerNameBox.setVisible(true);
        constraints.gridx = 3;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(playerNameBox, constraints);
        buttonsAndThings.add(playerNameBox);
        
        //Row 5
        gridY++;
        //Col 1
        JLabel ticketLabel = new JLabel("# of Tickets: ");
        ticketLabel.setFont(defont);
        ticketLabel.setVisible(true);
        constraints.gridx = 2;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(ticketLabel, constraints);
        buttonsAndThings.add(ticketLabel);
        //Col 2
        JComboBox<String> ticketBox = new JComboBox<String>(comboBoxMandatoryOptions);
        ticketBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JComboBox<String> selectedBox = (JComboBox<String>)event.getSource();
                String choice = (String)selectedBox.getSelectedItem();
                switch(choice)
                {
                    case "G": raffleSettings.ticketBundleRow = 6; break;
                    case "F": raffleSettings.ticketBundleRow = 5; break;
                    case "E": raffleSettings.ticketBundleRow = 4; break;
                    case "D": raffleSettings.ticketBundleRow = 3; break;
                    case "C": raffleSettings.ticketBundleRow = 2; break;
                    case "B": raffleSettings.ticketBundleRow = 1; break;
                    case "A": raffleSettings.ticketBundleRow = 0; break;
                    default: break;
                }
            }
        });
        ticketBox.setFont(defont);
        ticketBox.setVisible(true);
        constraints.gridx = 3;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(ticketBox, constraints);
        buttonsAndThings.add(ticketBox);
        
        //Row 5
        gridY++;
        //Col 1
        JLabel rowNumberLabel = new JLabel("# of Columns: ");
        rowNumberLabel.setFont(defont);
        rowNumberLabel.setVisible(true);
        constraints.gridx = 2;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(rowNumberLabel, constraints);
        buttonsAndThings.add(rowNumberLabel);
        rows = new JTextField("#", 2);
        rows.setFont(defont);
        constraints.gridx = 3;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        frame.add(rows, constraints);
        buttonsAndThings.add(rows);
        
        //Last row centered - The button to actually start the parsing
        gridY++;
        Button parse = new Button("Ready to parse");
        parse.setFont(defont);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 4;
        constraints.ipady = 5;
        constraints.fill = GridBagConstraints.VERTICAL;
        frame.add(parse, constraints);
        buttonsAndThings.add(parse);
        
        frame.validate();
        frame.repaint();
        
        parse.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    String seedText = seed.getText().trim();
                    String fileText = filename.getText().trim();
                    String rowsText = rows.getText().trim();
                    if(seedText.charAt(0) == ('#'))
                    {
                        seedText = seedText.substring(1);
                    }
                    if(rowsText.charAt(0) == ('#'))
                    {
                        rowsText = rowsText.substring(1);
                    }
                    long seedLong = Long.parseLong(seedText);
                    RaffleIO reader = new RaffleIO();
                    raffle = reader.parseRaffle(fileText, raffleSettings);
                    raffle.setSeed(seedLong);
                    raffle.settings.numberOfColumns = Integer.parseInt(rowsText);
                }
                catch(Exception e)
                {
                    errorMessage(e);
                }
                while(!buttonsAndThings.isEmpty())
                {
                    frame.remove(buttonsAndThings.get(0));
                    buttonsAndThings.remove(0);
                }
                
                raffleDrawing();
            }
        });
    }
    
    //Same as above - dumb compliance reasons to put these here
    JTextArea winners;
    JCheckBox labelBox;
    
    private void raffleDrawing()
    {
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(15, 0, 0, 0);
        int gridY = 0;
        
        //Top explanation
        JLabel raffleLabel = new JLabel("The raffle has been set up and will output to raffle_winners.txt");
        raffleLabel.setFont(defont);
        raffleLabel.setVisible(true);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.VERTICAL;
        frame.add(raffleLabel, constraints);
        buttonsAndThings.add(raffleLabel);
        
        //Determine batch size
        gridY++;
        //Col 1
        JLabel batchSizeLabel = new JLabel("Batch size: ", JLabel.RIGHT);
        batchSizeLabel.setFont(defont);
        batchSizeLabel.setVisible(true);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(batchSizeLabel, constraints);
        buttonsAndThings.add(batchSizeLabel);
        //Col 2
        batchSize = new JTextField("10", 6);
        batchSize.setFont(defont);
        constraints.gridx = 1;
        constraints.gridy = gridY;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        frame.add(batchSize, constraints);
        buttonsAndThings.add(batchSize);
        
        //Repeats option
        //Col 1
        gridY++;
        labelBox = new JCheckBox("Repeats on", false);
        labelBox.setFont(defont);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.VERTICAL;
        labelBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                if (event.getStateChange()==ItemEvent.SELECTED) 
                {
                    raffle.settings.noRepeats = false;
                } 
                else 
                {
                    raffle.settings.noRepeats = true;
                }
            }
        });
        frame.add(labelBox, constraints);
        buttonsAndThings.add(labelBox);
        
        //Button for drawing
        gridY++;
        Button drawButton = new Button("Draw batch");
        drawButton.setFont(defont);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.VERTICAL;
        frame.add(drawButton, constraints);
        buttonsAndThings.add(drawButton);
        
        //Test Area displaying the drawn winners
        gridY++;
        winners = new JTextArea();
        winners.setBounds(50, 50, frame.getWidth() - 50, frame.getHeight() - 50);
        winners.setText("");
        winners.setFont(defont);
        winners.setLineWrap(true);
        winners.setWrapStyleWord(true);
        winners.setVisible(true);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(winners, constraints);
        buttonsAndThings.add(winners);
        
        frame.validate();
        frame.repaint();
        
        drawButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                ArrayList<Ticket> winnersList = null;
                try
                {
                    raffle.settings.batchSize = Integer.parseInt(batchSize.getText().trim());
                    winnersList = raffle.drawBatch();
                }
                catch(Exception e)
                {
                    errorMessage(e);
                }
                labelBox.setEnabled(false);
                StringBuilder winnersString = new StringBuilder();
                for(int i = 0; i < winnersList.size(); i++)
                {
                    winnersString.append(winnersList.get(i).toString());
                    winnersString.append("\n");
                }
                winners.setText(winnersString.toString());
                RaffleIO.writeWinners(winnersList, "raffle_winners.txt");
            }
        });
    }
    
    public void errorMessage(Exception e)
    {
        JOptionPane.showMessageDialog(frame, "An error has occured while setting up or reading the file:\n" + e.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}