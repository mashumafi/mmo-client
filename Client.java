import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.net.*;
//import java.util.*;
import java.io.*;
import java.sql.*;

public class Client extends JApplet implements Runnable, MouseListener, MouseMotionListener, ActionListener, KeyListener
{
	JDesktopPane desktop = new JDesktopPane();
	JInternalFrame frameStats = new JInternalFrame("Stats", false, true, false, false);
	JInternalFrame frameSkills = new JInternalFrame("Skills", false, true, false, false);
	JInternalFrame frameItems = new JInternalFrame("Items", true, true, false, false);
	JInternalFrame frameNavigator = new JInternalFrame("Navigator", true, true, false, false);
	Nav nav = new Nav();

	Inventory inventory = new Inventory();
	Equipment equipment = new Equipment();

	JInternalFrame frameEquipment = new JInternalFrame("Equipment", false, true, false, false);
	JContainer canvas = new JContainer();

	JInternalFrame frameChat = new JInternalFrame("Chat", true, true, false, false);
    JScrollPane chatSP;
	JTextArea chat;
	JTextField message;
	JButton send;

	JList JListInventory = new JList();
	JTextArea JTextAreaItems;
	JScrollPane TextAreaItemsSP;
	JScrollPane ItemSP;

	JList JListEquipment = new JList();
	JTextArea JTextAreaEquipment;
	JScrollPane TextAreaEquipmentSP;
	JScrollPane EquipmentSP;

	JInternalFrame frameLogin = new JInternalFrame("Login", false, false, false, false);
	JTextField NicknameTextField;
	JPasswordField PasswordPasswordField;
	JButton LoginButton;
	JButton SignupButton;

	JInternalFrame frameServers = new JInternalFrame("Login", false, false, false, false);
	JList ServerList;
	JList ChannelList;
	JButton ConnectButton;
	private String Server[][] = {{"Mashumafi","Mashumafi1"}};;
	private String Char[][][];

	Hero hero = new Hero();
	Map map = new Map();

	JInternalFrame frameCharacters = new JInternalFrame("Login", false, false, false, false);
	CharacterList Characters[] = new CharacterList[3];
	JButton PlayButton[] = new JButton[3];
	JButton CreateDeleteButton[] = new JButton[3];
	int ButtonNumber = 0;

	JInternalFrame frameCreateAccount = new JInternalFrame("Create Account", false, false, false, false);
	JTextField CreateAccountEmailTextField;
	JTextField CreateAccountNicknameTextField;
	JPasswordField CreateAccountPasswordPasswordField;
	JButton ConfirmCreateAccount;
	JButton CancelCreateAccount;

	JInternalFrame frameCreateCharacter = new JInternalFrame("Create Character", false, false, false, false);
	JTextField CreateCharacterTextField;
	JComboBox CreateCharacterGenderComboBox;
	JComboBox CreateCharacterHairColorComboBox;
	JComboBox CreateCharacterHairStyleComboBox;
	JComboBox CreateCharacterSkinColorComboBox;
	JComboBox CreateCharacterTopComboBox;
	JComboBox CreateCharacterBottomComboBox;
	JComboBox CreateCharacterShoesComboBox;
	JComboBox CreateCharacterWeaponsComboBox;
	JButton ConfirmCreateCharacter;
	JButton CancelCreateCharacter;
	BasicCharacter characterPreview;

	JInternalFrame frameDeleteCharacter = new JInternalFrame("Delete Character", false, false, false, false);
	JPasswordField DeleteCharacterPasswordField;
	JButton ConfirmDeleteCharacter;
	JButton CancelDeleteCharacter;

	boolean actionPerformed = false;

	JButton PreviousServers;
	JButton PreviousCharacters;

	int loop = 0;
	double looter = 0;
	boolean HorizontalMove = false, VerticalMove = false, fall = false;

    Keyboard keyboard = new Keyboard();

	Network login = new Network();//"71.58.9.96",8080
    Network network = new Network();

	Others others = new Others();
	GroundItems grounditems = new GroundItems();

	int PressedIndex, ReleasedIndex;
	Boolean ExitInventory, ExitEquipment;

	String folder;
	String[] HairStyle;
	ImageIcon[] HairColor;
	ImageIcon[] SkinColor;
	ImageIcon[] skinColors;
	ImageIcon[] topColors;
	ImageIcon[] bottomColors;
	ImageIcon[] shoesColors;
	String[] weaponTypes;

	class frameFocusListener implements MouseListener, MouseMotionListener
	{
		public void mouseClicked(MouseEvent e)
		{
			desktop.grabFocus();
		}

		public void mouseEntered(MouseEvent e)
		{
			//desktop.grabFocus();
		}

		public void mouseExited(MouseEvent e)
		{
		}

		public void mousePressed(MouseEvent e)
		{
			//desktop.grabFocus();
		}

		public void mouseReleased(MouseEvent e)
		{
		}

		public void mouseDragged(MouseEvent e)
		{
			//desktop.grabFocus();
		}

		public void mouseMoved(MouseEvent e)
		{
			//desktop.grabFocus();
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void keyPressed(KeyEvent e)
	{
		desktop.grabFocus();
		//if(network.started)
		switch(e.getKeyCode())
		{
			case 65:keyboard.A_PRESSED=true; break;
			case 66:keyboard.B_PRESSED=true; break;
			case 67:keyboard.C_PRESSED=true; break;
			case 68:keyboard.D_PRESSED=true; break;
			case 69:keyboard.E_PRESSED=true; break;
			case 70:keyboard.F_PRESSED=true; break;
			case 71:keyboard.G_PRESSED=true; break;
			case 72:keyboard.H_PRESSED=true; break;
			case 73:keyboard.I_PRESSED=true; break;
			case 74:keyboard.J_PRESSED=true; break;
			case 75:keyboard.K_PRESSED=true; break;
			case 76:keyboard.L_PRESSED=true; break;
			case 77:keyboard.M_PRESSED=true; break;
			case 78:keyboard.N_PRESSED=true; break;
			case 79:keyboard.O_PRESSED=true; break;
			case 80:keyboard.P_PRESSED=true; break;
			case 81:keyboard.Q_PRESSED=true; break;
			case 82:keyboard.R_PRESSED=true; break;
			case 83:keyboard.S_PRESSED=true; break;
			case 84:keyboard.T_PRESSED=true; break;
			case 85:keyboard.U_PRESSED=true; break;
			case 86:keyboard.V_PRESSED=true; break;
			case 87:keyboard.W_PRESSED=true; break;
			case 88:keyboard.X_PRESSED=true; break;
			case 89:keyboard.Y_PRESSED=true; break;
			case 90:keyboard.Z_PRESSED=true; break;
			case KeyEvent.VK_ENTER:keyboard.ENTER_PRESSED=true; break;
			case KeyEvent.VK_LEFT: keyboard.LEFT_PRESSED=true; break;
			case KeyEvent.VK_UP: keyboard.UP_PRESSED=true; break;
			case KeyEvent.VK_RIGHT: keyboard.RIGHT_PRESSED=true; break;
			case KeyEvent.VK_DOWN: keyboard.DOWN_PRESSED=true; break;
			case KeyEvent.VK_SPACE: keyboard.SPACE_PRESSED=true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		desktop.grabFocus();
		//if(network.started)
		switch(e.getKeyCode())
		{
			case 65:keyboard.A_PRESSED=false; keyboard.A_ONCE = false; break;
			case 66:keyboard.B_PRESSED=false; keyboard.B_ONCE = false; break;
			case 67:keyboard.C_PRESSED=false; keyboard.C_ONCE = false; break;
			case 68:keyboard.D_PRESSED=false; keyboard.D_ONCE = false; break;
			case 69:keyboard.E_PRESSED=false; keyboard.E_ONCE = false; break;
			case 70:keyboard.F_PRESSED=false; keyboard.F_ONCE = false; break;
			case 71:keyboard.G_PRESSED=false; keyboard.G_ONCE = false; break;
			case 72:keyboard.H_PRESSED=false; keyboard.H_ONCE = false; break;
			case 73:keyboard.I_PRESSED=false; keyboard.I_ONCE = false; break;
			case 74:keyboard.J_PRESSED=false; keyboard.J_ONCE = false; break;
			case 75:keyboard.K_PRESSED=false; keyboard.K_ONCE = false; break;
			case 76:keyboard.L_PRESSED=false; keyboard.L_ONCE = false; break;
			case 77:keyboard.M_PRESSED=false; keyboard.M_ONCE = false; break;
			case 78:keyboard.N_PRESSED=false; keyboard.N_ONCE = false; break;
			case 79:keyboard.O_PRESSED=false; keyboard.O_ONCE = false; break;
			case 80:keyboard.P_PRESSED=false; keyboard.P_ONCE = false; break;
			case 81:keyboard.Q_PRESSED=false; keyboard.Q_ONCE = false; break;
			case 82:keyboard.R_PRESSED=false; keyboard.R_ONCE = false; break;
			case 83:keyboard.S_PRESSED=false; keyboard.S_ONCE = false; break;
			case 84:keyboard.T_PRESSED=false; keyboard.T_ONCE = false; break;
			case 85:keyboard.U_PRESSED=false; keyboard.U_ONCE = false; break;
			case 86:keyboard.V_PRESSED=false; keyboard.V_ONCE = false; break;
			case 87:keyboard.W_PRESSED=false; keyboard.W_ONCE = false; break;
			case 88:keyboard.X_PRESSED=false; keyboard.X_ONCE = false; break;
			case 89:keyboard.Y_PRESSED=false; keyboard.Y_ONCE = false; break;
			case 90:keyboard.Z_PRESSED=false; keyboard.Z_ONCE = false; break;
			case KeyEvent.VK_ENTER: keyboard.ENTER_PRESSED=false; keyboard.ENTER_ONCE = false; break;
			case KeyEvent.VK_LEFT: keyboard.LEFT_PRESSED=false; break;
			case KeyEvent.VK_UP: keyboard.UP_PRESSED=false; break;
			case KeyEvent.VK_RIGHT: keyboard.RIGHT_PRESSED=false; break;
			case KeyEvent.VK_DOWN: keyboard.DOWN_PRESSED=false; break;
			case KeyEvent.VK_SPACE: keyboard.SPACE_PRESSED=false; break;
		}
	}

	public void init()
	{

		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
		}

		desktop.addKeyListener(this);
		message.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
			}
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					network.send("Send:"+message.getText()+":");
					message.setText("");
					desktop.grabFocus();
				}
			}
			public void keyReleased(KeyEvent e)
			{
			}
		});

		ServerList.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent lse)
			{
				//if(Server[ServerList.getSelectedIndex()].length-1 <= 0)
				{
					String temp[] = new String[1];//Server[ServerList.getSelectedIndex()].length-1
					try
					{
						for(int i = 1; i < Server[ServerList.getSelectedIndex()].length; i++)
						{
							temp[i-1] = Server[ServerList.getSelectedIndex()][i];
						}
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					ChannelList.setListData(temp);
					ChannelList.setSelectedIndex(0);
				}
			}
		});

		JListInventory.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				JTextAreaItems.setText("");
				int i = JListInventory.getSelectedIndex();
				if(i>-1)
				{
					//JTextAreaItems.append(Integer.toString(inventory.ID[i]));
					if(!inventory.itemsStrings[i].equals("null"))
					{
						String temp1;
						String temp2;
						String total = "";
						total+="Name: "+inventory.itemsStrings[i]+"\n"+"Req Lvl: "+(inventory.ReqLevel[i])+"\n"+"Type: "+inventory.Type[i]+"\n";


						temp1 = "Req Str: "+(inventory.ReqStrength[i]);
						temp2 = "Add Str: "+(inventory.AddStrength[i])+"\n";
						total+=temp1+"\t"+temp2;

						temp1 = "Req Dex: "+(inventory.ReqDexterity[i]);
						temp2 = "Add Dex: "+(inventory.AddDexterity[i])+"\n";
						total+=temp1+"\t"+temp2;


						temp1 = "Req Int: "+(inventory.ReqIntelegence[i]);
						temp2 = "Add Int: "+(inventory.AddIntelegence[i])+"\n";
						total+=temp1+"\t"+temp2;
						JTextAreaItems.setText(total);
					}
				}
			}
		});

		JListEquipment.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				JTextAreaEquipment.setText("");
				int i = JListEquipment.getSelectedIndex();
				if(i>-1)
				{
					//JTextAreaItems.append(Integer.toString(inventory.ID[i]));
					if(!equipment.itemsStrings[i].equals("null"))
					{
						String temp1;
						String temp2;
						String total = "";
						total+="Name: "+equipment.itemsStrings[i]+"\n"+"Req Lvl: "+(equipment.ReqLevel[i])+"\n"+"Type: "+equipment.Type[i]+"\n";


						temp1 = "Req Str: "+(equipment.ReqStrength[i]);
						temp2 = "Add Str: "+(equipment.AddStrength[i])+"\n";
						total+=temp1+"\t"+temp2;

						temp1 = "Req Dex: "+(equipment.ReqDexterity[i]);
						temp2 = "Add Dex: "+(equipment.AddDexterity[i])+"\n";
						total+=temp1+"\t"+temp2;


						temp1 = "Req Int: "+(equipment.ReqIntelegence[i]);
						temp2 = "Add Int: "+(equipment.AddIntelegence[i])+"\n";
						total+=temp1+"\t"+temp2;
						JTextAreaEquipment.setText(total);
					}
				}
			}
		});

		JListInventory.addMouseListener(new MouseListener()
		{
			private int button;
			public void mouseClicked(MouseEvent e)
			{
				desktop.grabFocus();
			}

			public void mouseEntered(MouseEvent e)
			{
				ExitInventory = false;
			}

			public void mouseExited(MouseEvent e)
			{
				ExitInventory = true;
			}

			public void mousePressed(MouseEvent e)
			{
				PressedIndex = JListInventory.getSelectedIndex();
				switch(e.getButton())
				{
					case MouseEvent.BUTTON1:
						button = 1;
						break;
					case MouseEvent.BUTTON2:
						button = 2;
						break;
					case MouseEvent.BUTTON3:
						button = 3;
						break;
					default:
						button = 1;
				}
				if(button==1 && inventory.itemsStrings[PressedIndex]!="null")// && e.getClickCount()%2==1
				{
					Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("Graphics/Item Icons/"+inventory.Type[PressedIndex]+"/"+inventory.itemsStrings[PressedIndex]+".png"), new Point(0,0), "Item");
					frameItems.setCursor(cursor);
					frameEquipment.setCursor(cursor);
					desktop.setCursor(cursor);
					desktop.grabFocus();
				}
				if (button==1 && e.getClickCount()%2==0 && inventory.itemsStrings[PressedIndex]!="null")
				{
					chat.append("equip");
					network.send("EquipItem:"+(PressedIndex)+":");
				}
				desktop.grabFocus();
			}

			public void mouseReleased(MouseEvent e)
			{
				if(!ExitInventory && inventory.itemsStrings[PressedIndex]!="null")
				{
					ReleasedIndex = JListInventory.getSelectedIndex();
					network.send("SwapItems:"+(PressedIndex)+":"+(ReleasedIndex)+":");
				}
				if(ExitInventory && inventory.itemsStrings[PressedIndex]!="null")
				{
					network.send("Drop:"+(PressedIndex)+":"+(map.current)+":"+(hero.x+8)+":"+(hero.y)+":");
				}
				frameItems.setCursor(Cursor.getDefaultCursor());
				frameEquipment.setCursor(Cursor.getDefaultCursor());
				desktop.setCursor(Cursor.getDefaultCursor());
				desktop.grabFocus();
			}
		});

		JListEquipment.addMouseListener(new MouseListener()
		{
			private int button;
			public void mouseClicked(MouseEvent e)
			{
				desktop.grabFocus();
			}

			public void mouseEntered(MouseEvent e)
			{
			}

			public void mouseExited(MouseEvent e)
			{
			}

			public void mousePressed(MouseEvent e)
			{

				PressedIndex = JListEquipment.getSelectedIndex();
				switch(e.getButton())
				{
					case MouseEvent.BUTTON1:
						button = 1;
						break;
					case MouseEvent.BUTTON2:
						button = 2;
						break;
					case MouseEvent.BUTTON3:
						button = 3;
						break;
					default:
						button = 1;
				}
				if (button==1 && e.getClickCount()%2==0 && equipment.itemsStrings[PressedIndex]!="null")
				{
					network.send("UnequipItem:"+(PressedIndex)+":");
				}
				desktop.grabFocus();
			}

			public void mouseReleased(MouseEvent e)
			{
				desktop.grabFocus();
			}
		});

		desktop.addMouseListener(this);
		desktop.addMouseMotionListener(this);

		frameStats.addMouseListener(new frameFocusListener());//addMouseMotionListener
		frameStats.addMouseListener(new frameFocusListener());
		frameSkills.addMouseListener(new frameFocusListener());
		frameSkills.addMouseListener(new frameFocusListener());
		frameItems.addMouseListener(new frameFocusListener());
		frameItems.addMouseListener(new frameFocusListener());
		frameEquipment.addMouseListener(new frameFocusListener());
		frameEquipment.addMouseListener(new frameFocusListener());
		frameChat.addMouseListener(new frameFocusListener());
		frameChat.addMouseListener(new frameFocusListener());
		frameNavigator.addMouseListener(new frameFocusListener());
		nav.addMouseListener(new frameFocusListener());
		JListInventory.addMouseListener(new frameFocusListener());

		send.addActionListener(this);
		LoginButton.addActionListener(this);
		ConnectButton.addActionListener(this);
		PlayButton[0].addActionListener(this);
		PlayButton[1].addActionListener(this);
		PlayButton[2].addActionListener(this);
		CreateDeleteButton[0].addActionListener(this);
		CreateDeleteButton[1].addActionListener(this);
		CreateDeleteButton[2].addActionListener(this);
		ConfirmCreateCharacter.addActionListener(this);
		CancelCreateCharacter.addActionListener(this);
		ConfirmDeleteCharacter.addActionListener(this);
		CancelDeleteCharacter.addActionListener(this);
		PreviousServers.addActionListener(this);
		PreviousCharacters.addActionListener(this);
		SignupButton.addActionListener(this);
		ConfirmCreateAccount.addActionListener(this);
		CancelCreateAccount.addActionListener(this);

		new Thread(this).start();
	}

	private void jbInit() throws Exception
	{
		String[] HairStyle = {"Basic"};
		folder = "Graphics/Character/Basic Colors/Hair Colors/";
		ImageIcon[] HairColor = {new ImageIcon(folder+"black.png")};
		folder = "Graphics/Character/Basic Colors/Skin Color/";
		ImageIcon[] skinColors = {new ImageIcon(folder+"pale.png")};
		folder = "Graphics/Character/Basic Colors/Top Colors/";
		ImageIcon[] topColors = {new ImageIcon(folder+"red.png")};
		folder = "Graphics/Character/Basic Colors/Bottom Colors/";
		ImageIcon[] bottomColors = {new ImageIcon(folder+"blue.png")};
		folder = "Graphics/Character/Basic Colors/Shoes Colors/";
		ImageIcon[] shoesColors = {new ImageIcon(folder+"brown.png")};
		String[] weaponTypes = {"Sword"};

		int sizex = 800, sizey = 600;
		desktop.add(canvas);
		desktop.add(frameStats);
		desktop.add(frameSkills);
		desktop.add(frameItems);
		desktop.add(frameEquipment);
		desktop.add(frameChat);
		desktop.add(frameLogin);
		desktop.add(frameServers);
		desktop.add(frameCharacters);
		desktop.add(frameCreateCharacter);
		desktop.add(frameDeleteCharacter);
		desktop.add(frameCreateAccount);
		desktop.add(frameNavigator);

		{
			frameNavigator.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			frameNavigator.add(nav, c);
		}

		{
			frameItems.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;
			c.gridx = 0;
			c.gridy = 0;
			JListInventory.setVisibleRowCount(5);
			JListInventory.setFixedCellWidth(36);
			JListInventory.setFixedCellHeight(36);
			JListInventory.setLayoutOrientation(JList.VERTICAL_WRAP);
			JListInventory.setValueIsAdjusting(true);
			//JListInventory.setDragEnabled(true);
			frameItems.add(ItemSP = new JScrollPane(JListInventory), c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weighty = 0;
			c.ipady = 90;
			c.gridy = 1;
			frameItems.add(TextAreaItemsSP = new JScrollPane(JTextAreaItems = new JTextArea()), c);
			JTextAreaItems.setTabSize(20);

			JTextAreaItems.setFont(new Font("Courier",0,12));

			JTextAreaItems.setLineWrap(true);
			JTextAreaItems.setEditable(false);
			//JTextAreaItems.setAutoscrolls(true);
		}

		{
			frameEquipment.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;
			c.gridx = 0;
			c.gridy = 0;
			JListEquipment.setVisibleRowCount(6);//Equipment
			JListEquipment.setFixedCellWidth(36);
			JListEquipment.setFixedCellHeight(36);
			JListEquipment.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			String test[] = {"","","","","","","","","","","","","","","","","","","","","","","",""};
			JListEquipment.setListData(test);
			frameEquipment.add(EquipmentSP = new JScrollPane(JListEquipment), c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weighty = 0;
			c.ipady = 90;
			c.gridy = 1;
			frameEquipment.add(TextAreaEquipmentSP = new JScrollPane(JTextAreaEquipment = new JTextArea()), c);
			JTextAreaEquipment.setTabSize(20);

			JTextAreaEquipment.setFont(new Font("Courier",0,12));

			JTextAreaEquipment.setLineWrap(true);
			JTextAreaEquipment.setEditable(false);
			//JTextAreaEquipment.setAutoscrolls(true);
		}

		{
			frameChat.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 1;
			c.gridwidth = 2;
			frameChat.add(chatSP = new JScrollPane(chat = new JTextArea()), c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.PAGE_END;
			c.weighty = 0;
			c.gridwidth = 1;
			c.gridx = 0;
			//c.ipady = 0;
			c.weighty = 0;
			c.gridy = 1;
			frameChat.add(message = new JTextField(), c);
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0;
			frameChat.add(send = new JButton("Send"), c);

			chat.setLineWrap(true);
			chat.setEditable(false);
			chat.setAutoscrolls(true);
			frameChat.setMaximumSize(new Dimension(Global.ScreenWidth/2,Global.ScreenHeight/2));
			frameChat.setMinimumSize(new Dimension(Global.ScreenWidth/4,Global.ScreenHeight/4));
		}

		{
			frameLogin.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 0.3;
			c.gridwidth = 2;
			frameLogin.add(NicknameTextField = new JTextField(""), c);
			c.gridx = 1;
			c.gridy = 1;
			frameLogin.add(PasswordPasswordField = new JPasswordField(), c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			frameLogin.add(LoginButton = new JButton("Login"), c);
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 1;
			frameLogin.add(SignupButton = new JButton("Signup"), c);
			c.anchor = GridBagConstraints.LINE_START;
			c.weightx = 0;
			c.gridx = 0;
			c.gridy = 0;
			frameLogin.add(new Label("Nickname:"), c);
			c.gridx = 0;
			c.gridy = 1;
			frameLogin.add(new Label("Password:"), c);
		}

		{
			frameServers.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.insets.right = 5;
			c.ipadx=110;
			c.gridx = 0;
			c.gridy = 1;
			c.weighty = 0.9;
			frameServers.add(ServerList = new JList(), c);
			ServerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			c.insets.right = 0;
			c.gridx = 1;
			c.gridy = 1;
			frameServers.add(ChannelList = new JList(), c);
			ChannelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LAST_LINE_END;
			c.ipadx=0;
			c.weighty = 0;
			c.gridx = 1;
			c.gridy = 2;
			frameServers.add(ConnectButton = new JButton("Connect"), c);
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			frameServers.add(new Label("Servers:"), c);
			c.gridx = 1;
			c.gridy = 0;
			frameServers.add(new Label("Channels:"), c);
			c.anchor = GridBagConstraints.LAST_LINE_START;
			c.gridx = 0;
			c.gridy = 3;
			frameServers.add(PreviousServers = new JButton("Log Out"), c);
		}

		{
			frameCharacters.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.insets.left = 5;
			c.insets.top = 5;
			c.insets.right = 5;
			c.insets.bottom = 5;
			c.ipady=100;
			c.ipadx=100;
			c.gridx = 0;
			c.gridy = 1;
			frameCharacters.add(Characters[0] = new CharacterList(0,100,100), c);
			c.gridx = 1;
			frameCharacters.add(Characters[1] = new CharacterList(1,100,100), c);
			c.gridx = 2;
			frameCharacters.add(Characters[2] = new CharacterList(2,100,100), c);
			c.ipady=0;
			c.ipadx=0;
			c.gridx = 0;
			c.gridy = 2;
			frameCharacters.add(PlayButton[0] = new JButton("Play"), c);
			c.gridx = 1;
			frameCharacters.add(PlayButton[1] = new JButton("Play"), c);
			c.gridx = 2;
			frameCharacters.add(PlayButton[2] = new JButton("Play"), c);
			c.gridx = 0;
			c.gridy = 3;
			frameCharacters.add(CreateDeleteButton[0] = new JButton("Create"), c);
			c.gridx = 1;
			frameCharacters.add(CreateDeleteButton[1] = new JButton("Create"), c);
			c.gridx = 2;
			frameCharacters.add(CreateDeleteButton[2] = new JButton("Create"), c);
			c.anchor = GridBagConstraints.LAST_LINE_START;
			c.gridx = 0;
			c.gridy = 4;
			frameCharacters.add(PreviousCharacters = new JButton("Previous"), c);
		}

		{
			frameCreateCharacter.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.gridx = 0;
			c.gridy = 0;
			frameCreateCharacter.add(new Label("Name:"), c);
			c.gridx = 1;
			c.ipadx=100;
			c.insets.bottom = 10;
			frameCreateCharacter.add(CreateCharacterTextField = new JTextField(""), c);
			c.gridx = 0;
			c.gridy = 1;
			c.ipadx=0;
			frameCreateCharacter.add(new Label("Gender:"), c);
			c.gridx = 1;
			String[] gender = {"Male","Female"};
			//c.ipadx=30;
			frameCreateCharacter.add(CreateCharacterGenderComboBox = new JComboBox(gender), c);
			c.gridx = 0;
			c.gridy = 2;
			frameCreateCharacter.add(new Label("Hair Color:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterHairColorComboBox = new JComboBox(HairColor), c);
			c.gridx = 0;
			c.gridy = 3;
			frameCreateCharacter.add(new Label("Hair Style:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterHairStyleComboBox = new JComboBox(HairStyle), c);
			c.gridx = 0;
			c.gridy = 4;
			frameCreateCharacter.add(new Label("Skin Color:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterSkinColorComboBox = new JComboBox(skinColors), c);
			c.gridx = 0;
			c.gridy = 5;
			frameCreateCharacter.add(new Label("Top Color:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterTopComboBox = new JComboBox(topColors), c);
			c.gridx = 0;
			c.gridy = 6;
			frameCreateCharacter.add(new Label("Bottom Color:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterBottomComboBox = new JComboBox(bottomColors), c);
			c.gridx = 0;
			c.gridy = 7;
			frameCreateCharacter.add(new Label("Shoes Color:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterShoesComboBox = new JComboBox(shoesColors), c);
			c.gridx = 0;
			c.gridy = 8;
			frameCreateCharacter.add(new Label("Weapon Type:"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CreateCharacterWeaponsComboBox = new JComboBox(weaponTypes), c);
			c.ipadx=0;
			c.gridx = 0;
			c.gridy = 9;
			frameCreateCharacter.add(ConfirmCreateCharacter = new JButton("Confirm"), c);
			c.gridx = 1;
			frameCreateCharacter.add(CancelCreateCharacter = new JButton("Cancel"), c);
			c.ipadx = 100;
			c.ipady = 100;
			c.gridx = 3;
			c.gridy = 0;
			c.gridheight = 7;
			frameCreateCharacter.add(characterPreview = new BasicCharacter(), c);
		}

		{
			frameDeleteCharacter.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.gridx = 0;
			c.gridy = 0;
			frameDeleteCharacter.add(new Label("Password:"), c);
			c.gridx = 1;
			c.ipadx=100;
			c.insets.bottom = 10;
			frameDeleteCharacter.add(DeleteCharacterPasswordField = new JPasswordField(""), c);
			c.ipadx=0;
			c.gridx = 0;
			c.gridy = 1;
			frameDeleteCharacter.add(ConfirmDeleteCharacter = new JButton("Confirm"), c);
			c.gridx = 1;
			frameDeleteCharacter.add(CancelDeleteCharacter = new JButton("Cancel"), c);
		}

		{
			frameCreateAccount.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.gridx = 0;
			c.gridy = 0;
			frameCreateAccount.add(new Label("Email:"), c);
			c.gridx = 1;
			c.ipadx=100;
			frameCreateAccount.add(CreateAccountEmailTextField = new JTextField(""), c);
			c.ipadx=0;
			c.gridx = 0;
			c.gridy = 1;
			frameCreateAccount.add(new Label("Nickname:"), c);
			c.gridx = 1;
			c.ipadx=100;
			frameCreateAccount.add(CreateAccountNicknameTextField = new JTextField(""), c);
			c.ipadx=0;
			c.gridx = 0;
			c.gridy = 2;
			frameCreateAccount.add(new Label("Password:"), c);
			c.gridx = 1;
			c.ipadx=100;
			frameCreateAccount.add(CreateAccountPasswordPasswordField = new JPasswordField(""), c);
			c.ipadx=0;
			c.gridx = 0;
			c.gridy = 3;
			frameCreateAccount.add(ConfirmCreateAccount = new JButton("Confirm"), c);
			c.gridx = 1;
			frameCreateAccount.add(CancelCreateAccount = new JButton("Cancel"), c);
		}

		this.setSize(new Dimension(sizex,sizey));
		this.getContentPane().add(desktop, BorderLayout.CENTER);

		canvas.SetUp(800,600);
		Characters[0].SetUp(100,100);
		Characters[1].SetUp(100,100);
		Characters[2].SetUp(100,100);
		characterPreview.setUp();

		frameStats.setLocation(20, 20);
		frameStats.setSize(200, 150);
		//frameStats.setVisible(true);

		frameSkills.setLocation(40, 40);
		frameSkills.setSize(200, 150);
		//frameSkills.setVisible(true);

		frameItems.setLocation(60, 60);
		frameItems.setSize(320, 330);
		//frameItems.setVisible(true);

		frameEquipment.setLocation(80, 80);
		frameEquipment.setSize(320, 366);
		//frameEquipment.setVisible(true);

		frameChat.setLocation(100, 100);
		frameChat.setSize(200, 150);
		//frameChat.setVisible(true);

		frameLogin.setLocation(200, 150);
		frameLogin.setSize(200, 150);
		frameLogin.setVisible(true);

		frameServers.setLocation(200, 150);
		frameServers.setSize(400, 200);
		frameServers.setVisible(false);

		frameCharacters.setLocation(200, 150);
		frameCharacters.setSize(350, 260);
		frameCharacters.setVisible(false);

		frameCreateCharacter.setLocation(200, 150);
		frameCreateCharacter.setSize(400, 425);
		frameCreateCharacter.setVisible(false);

		frameDeleteCharacter.setLocation(200, 150);
		frameDeleteCharacter.setSize(200, 150);
		frameDeleteCharacter.setVisible(false);

		frameCreateAccount.setLocation(200, 150);
		frameCreateAccount.setSize(200, 150);
		frameCreateAccount.setVisible(false);

		frameNavigator.setLocation(200, 150);
		frameNavigator.setSize(200, 150);
		frameNavigator.setVisible(false);
	}

	public void start()
	{
	}

	public void stop()
	{
	}

	public void destroy()
	{
	}

	public void run()
	{
		while(true)
		{
			loop++;
			try
			{

				if(frameCharacters.isVisible() && !frameCharacters.isClosed())
				{
					Characters[0].repaint();
					Characters[1].repaint();
					Characters[2].repaint();
				}

				if(frameCreateCharacter.isVisible() && !frameCreateCharacter.isClosed())
				{
					characterPreview.repaint();
				}

				if(!login.started & !frameCreateAccount.isVisible())
				{
					frameLogin.setVisible(true);
					if(network.started)
					network = new Network();
					frameNavigator.setVisible(false);
					frameCreateAccount.setVisible(false);
					frameDeleteCharacter.setVisible(false);
					frameCreateCharacter.setVisible(false);
					frameCharacters.setVisible(false);
					frameServers.setVisible(false);
					frameChat.setVisible(false);
					frameEquipment.setVisible(false);
					frameItems.setVisible(false);
					frameSkills.setVisible(false);
					frameStats.setVisible(false);
				}
				else if(!login.started & frameCreateAccount.isVisible())
				{
					if(network.started)
					network = new Network();
					frameNavigator.setVisible(false);
					frameDeleteCharacter.setVisible(false);
					frameCreateCharacter.setVisible(false);
					frameCharacters.setVisible(false);
					frameServers.setVisible(false);
					frameChat.setVisible(false);
					frameEquipment.setVisible(false);
					frameItems.setVisible(false);
					frameSkills.setVisible(false);
					frameStats.setVisible(false);
				}

				if(loop%100==0 && actionPerformed && network.started)
				{
					network.send("MoveMapXY:"+ (map.current) + ":" + (hero.x) + ":" + (hero.y) + ":" + (hero.frame) + ":" + (hero.flip) + ":");
					actionPerformed=false;
				}

				for(int i = 0; i < map.room[map.current].door.length; i++)
				{
					if(map.room[map.current].door[i].auto)
					{
						if(hero.y+50 == map.room[map.current].door[i].y && map.room[map.current].door[i].domain(hero.x+25))
						{
							map.room[map.current].door[i].transport();
						}
					}
				}
				Thread.sleep(1);
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == send)
		{
			network.send("Send:"+message.getText()+":");
			message.setText("");
			desktop.grabFocus();
		}
		if(ae.getSource() == LoginButton)
		{
			if(!login.started)
			login = new Network("71.58.9.96",8080);
			if(login.started)
			login.send("Login:" + NicknameTextField.getText() + ":" + charArraytoString(PasswordPasswordField.getPassword()) + ":");
		}
		if(ae.getSource() == ConnectButton)
		{
			frameServers.setVisible(false);
			frameCharacters.setVisible(true);
			for(int i = 0; i < Characters.length; i++)
			{
				Characters[i].CreateList(Char[ServerList.getSelectedIndex()][i]);
			}
		}
		if(ae.getSource() == PlayButton[0])
		{
			ButtonNumber=0;
			login.send("Play:" + Char[ServerList.getSelectedIndex()][ButtonNumber][0] + ":" + (ServerList.getSelectedIndex()) + ":" + (ChannelList.getSelectedIndex()) + ":");
		}
		if(ae.getSource() == PlayButton[1])
		{
			ButtonNumber=1;
			login.send("Play:" + Char[ServerList.getSelectedIndex()][ButtonNumber][0] + ":" + (ServerList.getSelectedIndex()) + ":" + (ChannelList.getSelectedIndex()) + ":");
		}
		if(ae.getSource() == PlayButton[2])
		{
			ButtonNumber=2;
			login.send("Play:" + Char[ServerList.getSelectedIndex()][ButtonNumber][0] + ":" + (ServerList.getSelectedIndex()) + ":" + (ChannelList.getSelectedIndex()) + ":");
		}
		if(ae.getSource() == CreateDeleteButton[0])
		{
			if(CreateDeleteButton[0].getText().equals("Create"))
			{
				frameCharacters.setVisible(false);
				frameCreateCharacter.setVisible(true);
			}

			else if(CreateDeleteButton[0].getText().equals("Delete"))
			{
				frameCharacters.setVisible(false);
				frameDeleteCharacter.setVisible(true);
				ButtonNumber=0;
			}
		}
		if(ae.getSource() == CreateDeleteButton[1])
		{
			if(CreateDeleteButton[1].getText().equals("Create"))
			{
				frameCharacters.setVisible(false);
				frameCreateCharacter.setVisible(true);
			}
			else if(CreateDeleteButton[1].getText().equals("Delete"))
			{
				frameCharacters.setVisible(false);
				frameDeleteCharacter.setVisible(true);
				ButtonNumber=1;
			}
		}
		if(ae.getSource() == CreateDeleteButton[2])
		{
			if(CreateDeleteButton[2].getText().equals("Create"))
			{
				frameCharacters.setVisible(false);
				frameCreateCharacter.setVisible(true);
			}
			else if(CreateDeleteButton[2].getText().equals("Delete"))
			{
				frameCharacters.setVisible(false);
				frameDeleteCharacter.setVisible(true);
				ButtonNumber=2;
			}
		}
		if(ae.getSource() == ConfirmCreateCharacter)
		{
			login.send("CreateCharacter:" + (ServerList.getSelectedIndex()) + ":" + CreateCharacterTextField.getText() + ":" + CreateCharacterGenderComboBox.getSelectedIndex() + ":");
			CreateCharacterTextField.setText("");
		}
		if(ae.getSource() == CancelCreateCharacter)
		{
			frameCharacters.setVisible(true);
			frameCreateCharacter.setVisible(false);
			CreateCharacterTextField.setText("");
		}
		if(ae.getSource() == ConfirmDeleteCharacter)
		{
			login.send("DeleteCharacter:" + (ServerList.getSelectedIndex()) + ":" + Char[ServerList.getSelectedIndex()][ButtonNumber][0] + ":" + charArraytoString(DeleteCharacterPasswordField.getPassword()) + ":");
			DeleteCharacterPasswordField.setText("");
		}
		if(ae.getSource() == CancelDeleteCharacter)
		{
			frameCharacters.setVisible(true);
			frameDeleteCharacter.setVisible(false);
			DeleteCharacterPasswordField.setText("");
		}
		if(ae.getSource() == PreviousServers)
		{
			frameLogin.setVisible(true);
			frameServers.setVisible(false);
		}
		if(ae.getSource() == PreviousCharacters)
		{
			frameServers.setVisible(true);
			frameCharacters.setVisible(false);
		}
		if(ae.getSource() == SignupButton)
		{
			frameCreateAccount.setVisible(true);
			frameLogin.setVisible(false);
		}
		if(ae.getSource() == ConfirmCreateAccount)
		{
			if(!login.started)
			login = new Network("71.58.9.96",8080);
			if(login.started)
			login.send("CreateAccount:" + CreateAccountEmailTextField.getText() + ":" + CreateAccountNicknameTextField.getText() + ":" + charArraytoString(CreateAccountPasswordPasswordField.getPassword()) + ":");
		}
		if(ae.getSource() == CancelCreateAccount)
		{
			frameCreateAccount.setVisible(false);
			frameLogin.setVisible(true);
			CreateAccountEmailTextField.setText("");
			CreateAccountNicknameTextField.setText("");
			CreateAccountPasswordPasswordField.setText("");
		}

	}

	public String charArraytoString(char[] c)
	{
		String test = "";
		for(int i = 0; i < c.length; i++)
		{
			test += c[i];
		}
		return test;
	}

    private void checkButton(MouseEvent e) {
		switch(e.getButton())
		{
			case MouseEvent.BUTTON1:
				Mouse.button = 1;
				break;
			case MouseEvent.BUTTON2:
				Mouse.button = 2;
				break;
			case MouseEvent.BUTTON3:
				Mouse.button = 3;
				break;
			default:
				Mouse.button = 3;
		}
    }

    public void mouseClicked(MouseEvent e)
    {
		desktop.grabFocus();
		checkButton(e);
		Mouse.ClickedX = e.getX();
        Mouse.ClickedY = e.getY();
		Mouse.DraggedX = Mouse.ClickedX;
		Mouse.DraggedY = Mouse.ClickedY;
    }

    public void mouseEntered(MouseEvent e)
    {
		Mouse.EnteredX = e.getX();
        Mouse.EnteredY = e.getY();
    }

    public void mouseExited(MouseEvent e)
    {
		Mouse.ExitedX = e.getX();
        Mouse.ExitedY = e.getY();
    }

    public void mousePressed(MouseEvent e)
    {
		desktop.grabFocus();
		checkButton(e);
		Mouse.PressedX = e.getX();
        Mouse.PressedY = e.getY();
    }

    public void mouseReleased(MouseEvent e)
    {
		checkButton(e);
		Mouse.ReleasedX = e.getX();
        Mouse.ReleasedY = e.getY();
    }

    public void mouseDragged(MouseEvent e)
    {
		desktop.grabFocus();
		Mouse.DraggedX = e.getX();
        Mouse.DraggedY = e.getY();
    }

    public void mouseMoved(MouseEvent e)
    {
		Mouse.MovedX = e.getX();
        Mouse.MovedY = e.getY();
	}

	public void sleep(int miliseconds)
	{
		try
		{
			Thread.sleep(miliseconds);
		}
		catch(Exception e)
		{
			if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
		}
	}

	class Keyboard implements Runnable
	{
		int PressedCode, TypedCode;
		char PressedChar, TypedChar;
		boolean A_PRESSED,B_PRESSED,C_PRESSED,D_PRESSED,E_PRESSED,F_PRESSED,G_PRESSED,H_PRESSED,I_PRESSED,J_PRESSED,K_PRESSED,L_PRESSED,M_PRESSED,N_PRESSED,O_PRESSED,P_PRESSED,Q_PRESSED,R_PRESSED,S_PRESSED,T_PRESSED,U_PRESSED,V_PRESSED,W_PRESSED,X_PRESSED,Y_PRESSED,Z_PRESSED,LEFT_PRESSED,UP_PRESSED,RIGHT_PRESSED,DOWN_PRESSED,SPACE_PRESSED,ENTER_PRESSED;
		boolean A_ONCE,B_ONCE,C_ONCE,D_ONCE,E_ONCE,F_ONCE,G_ONCE,H_ONCE,I_ONCE,J_ONCE,K_ONCE,L_ONCE,M_ONCE,N_ONCE,O_ONCE,P_ONCE,Q_ONCE,R_ONCE,S_ONCE,T_ONCE,U_ONCE,V_ONCE,W_ONCE,X_ONCE,Y_ONCE,Z_ONCE,LEFT_ONCE,UP_ONCE,RIGHT_ONCE,DOWN_ONCE,SPACE_ONCE,ENTER_ONCE;

		Keyboard()
		{
			new Thread(this).start();
		}

		public void run()
		{
			while(true)
			{
				sleep(25);
				if(A_PRESSED && !A_ONCE)
				{
					try
					{
						if(frameStats.isClosed())
						{
							frameStats.setClosed(false);
							desktop.add(frameStats);
							frameStats.setVisible(true);
							desktop.grabFocus();
						}
						frameStats.setVisible(!frameStats.isVisible());
						desktop.grabFocus();
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					A_ONCE = true;
				}
				if(B_PRESSED && !B_ONCE)
				{
					B_ONCE = true;
				}
				if(C_PRESSED && !C_ONCE)
				{
					C_ONCE = true;
				}
				if(D_PRESSED && !D_ONCE)
				{
					try
					{
						if(frameItems.isClosed())
						{
							frameItems.setClosed(false);
							desktop.add(frameItems);
							frameItems.setVisible(true);
							desktop.grabFocus();
						}
						frameItems.setVisible(!frameItems.isVisible());
						desktop.grabFocus();
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					D_ONCE = true;
				}
				if(E_PRESSED && !E_ONCE)
				{
					E_ONCE = true;
				}
				if(F_PRESSED && !F_ONCE)
				{
					try
					{
						if(frameEquipment.isClosed())
						{
							frameEquipment.setClosed(false);
							desktop.add(frameEquipment);
							frameEquipment.setVisible(true);
							desktop.grabFocus();
						}
						frameEquipment.setVisible(!frameEquipment.isVisible());
						desktop.grabFocus();
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					F_ONCE = true;
				}
				if(G_PRESSED && !G_ONCE)
				{
					G_ONCE = true;
				}
				if(H_PRESSED && !H_ONCE)
				{
					H_ONCE = true;
				}
				if(I_PRESSED && !I_ONCE)
				{
					I_ONCE = true;
				}
				if(J_PRESSED && !J_ONCE)
				{
					J_ONCE = true;
				}
				if(K_PRESSED && !K_ONCE)
				{
					K_ONCE = true;
				}
				if(L_PRESSED && !L_ONCE)
				{
					L_ONCE = true;
				}
				if(M_PRESSED && !M_ONCE)
				{
					M_ONCE = true;
				}
				if(N_PRESSED && !N_ONCE)
				{
					try
					{
						if(frameNavigator.isClosed())
						{
							frameNavigator.setClosed(false);
							desktop.add(frameNavigator);
							frameNavigator.setVisible(true);
							desktop.grabFocus();
						}
						frameNavigator.setVisible(!frameNavigator.isVisible());
						desktop.grabFocus();
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					N_ONCE = true;
				}
				if(O_PRESSED && !O_ONCE)
				{
					O_ONCE = true;
				}
				if(P_PRESSED && !P_ONCE)
				{
					P_ONCE = true;
				}
				if(Q_PRESSED && !Q_ONCE)
				{
					Q_ONCE = true;
				}
				if(R_PRESSED && !R_ONCE)
				{
					R_ONCE = true;
				}
				if(S_PRESSED && !S_ONCE)
				{
					try
					{
						if(frameSkills.isClosed())
						{
							frameSkills.setClosed(false);
							desktop.add(frameSkills);
							frameSkills.setVisible(true);
							desktop.grabFocus();
						}
						frameSkills.setVisible(!frameSkills.isVisible());
						desktop.grabFocus();
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					S_ONCE = true;
				}
				if(T_PRESSED && !T_ONCE)
				{
					T_ONCE = true;
				}
				if(U_PRESSED && !Y_ONCE)
				{
					Y_ONCE = true;
				}
				if(V_PRESSED && !V_ONCE)
				{
					V_ONCE = true;
				}
				if(W_PRESSED && !W_ONCE)
				{
					W_ONCE = true;
				}
				if(X_PRESSED && !X_ONCE)
				{
					X_ONCE = true;
				}
				if(Y_PRESSED && !Y_ONCE)
				{
					Y_ONCE = true;
				}
				if(Z_PRESSED && !Z_ONCE)
				{
					if(looter++%Double.MAX_VALUE==0)
					{
						looter=0;
						for(int i = 0; i < grounditems.count; i++)
						{
							if((hero.x+10 <= grounditems.x[i]+17 && hero.x+40 >= grounditems.x[i]+17) && (hero.y+15 >= grounditems.y[i] && hero.y+15 <= grounditems.y[i]))
							{
								network.send("PickUp:"+(map.current)+":"+(grounditems.x[i])+":"+(grounditems.y[i])+":");
							}
						}
					}
					//Z_ONCE = true;
				}
				if(ENTER_PRESSED && !ENTER_ONCE)
				{
					try
					{
						if(frameChat.isClosed())
						{
							frameChat.setClosed(false);
							desktop.add(frameChat);
							frameChat.setVisible(true);
							desktop.grabFocus();
						}
						//frameChat.setVisible(!frameChat.isVisible());
						frameChat.setVisible(true);
						desktop.grabFocus();
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
					message.grabFocus();
					ENTER_ONCE = true;
				}
				if(LEFT_PRESSED && !RIGHT_PRESSED && !hero.climbing)
				{
					hero.flip = true;
					HorizontalMove = true;
					for(int i = 0; i < map.room[map.current].wall.length; i++)
					{
						if(map.room[map.current].wall[i].range(hero.y) && map.room[map.current].wall[i].x == hero.x+15)
						{
							HorizontalMove = false;
						}
					}
					for(int i = 0; i < map.room[map.current].ledge.length; i++)
					{
						if ((hero.y+51 == map.room[map.current].ledge[i].function(hero.x+24)) && (map.room[map.current].ledge[i].domain(hero.x+24)))
						{
							hero.frameDelay++;
							if(hero.frameDelay%5 == 0)
								hero.frame++;
							if(34 < hero.frame && hero.frame < 41)
							{
							}
							else
							{
								hero.frame = 35;
							}
							if(HorizontalMove)
							hero.x--;
							hero.y++;
							actionPerformed=true;
							break;
						}
						if ((hero.y+49 == map.room[map.current].ledge[i].function(hero.x+24)) && (map.room[map.current].ledge[i].domain(hero.x+24)))
						{
							hero.frameDelay++;
							if(hero.frameDelay%5 == 0)
								hero.frame++;
							if(27 < hero.frame && hero.frame < 34)
							{
							}
							else
							{
								hero.frame = 28;
							}
							if(HorizontalMove)
							hero.x--;
							hero.y--;
							actionPerformed=true;
							break;
						}
						if ((hero.y+50 == map.room[map.current].ledge[i].function(hero.x+24)) && (map.room[map.current].ledge[i].domain(hero.x+24)))
						{
							hero.frameDelay++;
							if(hero.frameDelay%5 == 0)
								hero.frame++;
							if(6 < hero.frame && hero.frame < 14)
							{
							}
							else
							{
								hero.frame = 7;
							}
							if(HorizontalMove)
							hero.x--;
							actionPerformed=true;
							break;
						}
						if(i == map.room[map.current].ledge.length-1)
						{
							if(HorizontalMove)
							hero.x--;
							actionPerformed=true;
							break;
						}
					}
				}
				if(UP_PRESSED)
				{
					for(int i = 0; i < map.room[map.current].door.length; i++)
					{
						if(hero.y+50 == map.room[map.current].door[i].y && map.room[map.current].door[i].domain(hero.x+25))
						{
							map.room[map.current].door[i].transport();
						}
					}
					for(int i = 0; i < map.room[map.current].ladder.length; i++)
					{
						if(map.room[map.current].ladder[i].x == hero.x+25 && map.room[map.current].ladder[i].range(hero.y+49))
						{
							hero.climbing = true;
							break;
						}
						else
						{
							hero.climbing = false;
						}
					}
					if(hero.climbing)
					{
						hero.frameDelay++;
						if(hero.frameDelay%5 == 0)
							hero.frame++;
						if(20 < hero.frame && hero.frame < 27)
						{
						}
						else
						{
							hero.frame = 21;
						}
						hero.y--;
						actionPerformed=true;
					}
				}
				if(RIGHT_PRESSED && !LEFT_PRESSED && !hero.climbing)
				{
					hero.flip = false;
					HorizontalMove = true;
					for(int i = 0; i < map.room[map.current].wall.length; i++)
					{
						if(map.room[map.current].wall[i].range(hero.y) && map.room[map.current].wall[i].x == hero.x+35)
						{
							HorizontalMove = false;
						}
					}
					for(int i = 0; i < map.room[map.current].ledge.length; i++)
					{
						if ((hero.y+51 == map.room[map.current].ledge[i].function(hero.x+26)) && (map.room[map.current].ledge[i].domain(hero.x+26)))
						{
							hero.frameDelay++;
							if(hero.frameDelay%5 == 0)
								hero.frame++;
							if(34 < hero.frame && hero.frame < 41)
							{
							}
							else
							{
								hero.frame = 35;
							}
							if(HorizontalMove)
							hero.x++;
							hero.y++;
							actionPerformed=true;
							break;
						}
						if ((hero.y+49 == map.room[map.current].ledge[i].function(hero.x+26)) && (map.room[map.current].ledge[i].domain(hero.x+26)))
						{
							hero.frameDelay++;
							if(hero.frameDelay%5 == 0)
								hero.frame++;
							if(27 < hero.frame && hero.frame < 34)
							{
							}
							else
							{
								hero.frame = 28;
							}
							if(HorizontalMove)
							hero.x++;
							hero.y--;
							actionPerformed=true;
							break;
						}
						if ((hero.y+50 == map.room[map.current].ledge[i].function(hero.x+26)) && (map.room[map.current].ledge[i].domain(hero.x+26)))
						{
							hero.frameDelay++;
							if(hero.frameDelay%5 == 0)
								hero.frame++;
							if(6 < hero.frame && hero.frame < 14)
							{
							}
							else
							{
								hero.frame = 7;
							}
							if(HorizontalMove)
							hero.x++;
							actionPerformed=true;
							break;
						}
						if(i == map.room[map.current].ledge.length-1)
						{
							if(HorizontalMove)
							hero.x++;
							actionPerformed=true;
							break;
						}
					}
				}
				if(DOWN_PRESSED)
				{
					for(int i = 0; i < map.room[map.current].ladder.length; i++)
					{
						if(map.room[map.current].ladder[i].x == hero.x+25 && map.room[map.current].ladder[i].range(hero.y+51))
						{
							hero.climbing = true;
							break;
						}
						else
						{
							hero.climbing = false;
						}
					}
					if(!hero.climbing && !RIGHT_PRESSED && !LEFT_PRESSED)
					{
						for(int i = 0; i < map.room[map.current].ledge.length; i++)
						{
							if(((hero.y+50 == map.room[map.current].ledge[i].function(hero.x+25)) && (map.room[map.current].ledge[i].domain(hero.x+25))))
							{
								hero.frame = 5;
							}
						}
					}
					if(hero.climbing)
					{
						hero.frameDelay++;
						if(hero.frameDelay%5 == 0)
							hero.frame++;
						if(20 < hero.frame && hero.frame < 27)
						{
						}
						else
						{
							hero.frame = 21;
						}
						hero.y++;
						actionPerformed=true;
					}
				}
				if(SPACE_PRESSED)
				{
					if(hero.climbing && LEFT_PRESSED)
					{
						hero.jump = true;
						hero.jumpHeight = 20;
						hero.frame = 0;
						hero.climbing = false;
					}
					if(hero.climbing && RIGHT_PRESSED)
					{
						hero.jump = true;
						hero.jumpHeight = 20;
						hero.frame = 0;
						hero.climbing = false;
					}

					if(!DOWN_PRESSED)
					{
						VerticalMove = false;
						for(int i = 0; i < map.room[map.current].ledge.length; i++)
						{
							if(((hero.y+50 == map.room[map.current].ledge[i].function(hero.x+25)) && (map.room[map.current].ledge[i].domain(hero.x+25))))
							{
								VerticalMove = true;
							}
						}
						if(!hero.jump && VerticalMove)
						{
							hero.jump = true;
							hero.jumpHeight = 0;
							hero.frame = 0;
						}
					}
					if(DOWN_PRESSED)
					{
						for(int i = 0; i < map.room[map.current].ledge.length; i++)
						{
							if ((hero.y+50 == map.room[map.current].ledge[i].function(hero.x+25)) && (map.room[map.current].ledge[i].domain(hero.x+25)))
							{
								if(map.room[map.current].ledge[i].fall)
								{
									hero.y++;
									actionPerformed=true;
								}
							}
						}
					}
				}
			}
		}
	}

	class Nav extends Container
	{
		private Image image;
		private int x, y, width, height;
		private int drawX, drawY, drawWidth, drawHeight;
		private Dimension dim;
	    private Graphics bufferGraphics;
	    private Image offscreen;
	    private int[] xs, ys;
	    private int sLength;


		public void update(Graphics g)
		{
		}

		public void setUp(String[] data)
		{
			sLength = Integer.parseInt(data[1]);
			xs = new int[sLength];
			ys = new int[sLength];
			int xcount=0, ycount=0;
			for(int i = 2; i < data.length; i++)
			{
				if(data[i].equals("X"))
				{
					xs[xcount++] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("Y"))
				{
					ys[ycount++] = Integer.parseInt(data[++i]);
				}
			}
			nav.repaint();
		}

		public void setUp(Image image, int x, int y, int width, int height, int drawX, int drawY, int drawWidth, int drawHeight)
		{
			try
			{
				this.x = x;
				this.y = y;
				this.width = width;
				this.height = height;
				this.drawX = drawX-150;
				this.drawY = drawY-150;
				this.drawWidth = drawWidth+200;
				this.drawHeight = drawHeight+200;
				this.image = image;

				try
				{
					dim = getSize();
					offscreen = createImage(dim.width,dim.height);
					bufferGraphics = offscreen.getGraphics();
				}
				catch(Exception e)
				{
					if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
				}
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}
		public void paint(Graphics g)
		{
			dim = getSize();
			bufferGraphics.clearRect(0,0,(int)dim.getWidth(), (int)dim.getHeight());
			bufferGraphics.drawImage(image, 0, 0, (int)dim.getWidth(), (int)dim.getHeight(), drawX, drawY, drawWidth, drawHeight, desktop);
			g.drawImage(offscreen, 0, 0, (int)dim.getWidth(), (int)dim.getHeight(), nav);
		}

		public void run()
		{
		}
	}

	class JContainer extends Container implements Runnable
	{
	    Dimension dim;
	    Graphics bufferGraphics;
	    Image offscreen;
		Toolkit tk = Toolkit.getDefaultToolkit();
		int count = 0;
		int drawX,drawY,drawWidth,drawHeight;
		Image test;

		public void run()
		{
			while(true)
			{
				try
				{
					if(!hero.climbing)
					{
						if(!hero.jump)
						{
							fall = true;
							for(int i = 0; i < map.room[map.current].ledge.length; i++)
							{
								if((hero.y+50 == map.room[map.current].ledge[i].function(hero.x+25)) && (map.room[map.current].ledge[i].domain(hero.x+25)))
								{
									fall = false;
									if((!keyboard.LEFT_PRESSED && !keyboard.RIGHT_PRESSED && !keyboard.DOWN_PRESSED) || (keyboard.LEFT_PRESSED && keyboard.RIGHT_PRESSED))
									{
										hero.frame = 4;
										actionPerformed=true;
										break;
									}
								}
							}
							if(fall)
							{
								hero.y++;
								hero.frame = 3;
								actionPerformed=true;
							}
						}
						if(hero.jump)
						{
							for(int i = 0; i < map.room[map.current].ledge.length; i++)
							{
								if(hero.y+5 == map.room[map.current].ledge[i].function(hero.x+25) && map.room[map.current].ledge[i].domain(hero.x+25) && !map.room[map.current].ledge[i].jump)
								{
									hero.jumpHeight=40;
								}
							}
							hero.jumpHeight++;
							hero.y--;
							actionPerformed=true;
							if(hero.jumpHeight>40)
							{
								hero.jump = false;
							}
						}
					}
					Thread.sleep(10);
				}
				catch(Exception e)
				{
					if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
				}
			}
		}

		JContainer()
		{
			setSize(800, 600);
			test = tk.getImage("Graphics/Backgrounds/test.png");
			new Thread(this).start();
		}

		public void SetUp(int height, int width)
		{
			try
			{
				setSize(height, width);
				dim = getSize();
				offscreen = createImage(dim.width,dim.height);
				bufferGraphics = offscreen.getGraphics();
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public void paint(Graphics g)
		{
			if(network.started)
			{
				bufferGraphics.clearRect(0,0,dim.width,dim.width);
				bufferGraphics.setFont(new Font("Courier",0,12));
				bufferGraphics.drawImage(map.room[map.current].background, 0, 0, desktop);
				map.paint(bufferGraphics);
				grounditems.paint(bufferGraphics);
				others.paint(bufferGraphics);

				bufferGraphics.setColor(Color.ORANGE);

				for(int i = 0; i < nav.sLength; i++)
				{
					bufferGraphics.fillArc(nav.xs[i]+13,nav.ys[i],25,50,0,360);
				}

				count++;
				if(count>26)
				{
					count=0;
				}
				hero.paint(bufferGraphics);

				drawX = hero.x-150;
				drawY = hero.y-150;
				drawWidth = hero.x+200;
				drawHeight = hero.y+200;

				if(hero.x-150<0)
				{
					drawX = 0;
					drawWidth = 350;
				}

				if(hero.y-150<0)
				{
					drawY = 0;
					drawHeight = 350;
				}

				if(hero.x+200>map.room[map.current].sizex)
				{
					drawX = map.room[map.current].sizex-350;
					drawWidth = map.room[map.current].sizex;
				}

				if(hero.y+200>map.room[map.current].sizey)
				{
					drawY = map.room[map.current].sizey-350;
					drawHeight = map.room[map.current].sizey;
				}

				g.drawImage(offscreen, 0, 0, 800, 600, drawX, drawY, drawWidth, drawHeight, desktop);

				nav.setUp(offscreen, 0, 0, 800, 600, drawX, drawY, drawWidth, drawHeight);
			}
			else
			{
				g.clearRect(0,0,dim.width,dim.width);
			}
		}

		public void update(Graphics g)
		{
		}
	}

	public void drawFrame(Image source, Graphics dest,
		int x, int y, int cols, int frame,
		int width, int height, boolean flip) {
			int fx = (frame % cols) * width;
			int fy = (frame / cols) * height;
			if(!flip)
			dest.drawImage(source, x, y, x+width, y+height, fx, fy, fx+width, fy+height, desktop);
			if(flip)
			dest.drawImage(source, x+width, y, x, y+height, fx, fy, fx+width, fy+height, desktop);
	}

	URL getURL(String filename)
	{
		URL url = null;
		try
		{
			url = this.getClass().getResource(filename);
		}
		catch (Exception e)
		{
			if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
		}
		return url;
	}

	class Others
	{
		private int[] x, y, frame;
		private boolean flip[];
		private String[] name, gender;
		private int length;
		private Image[][] items;//, itemsMain;
		private Toolkit tk = Toolkit.getDefaultToolkit();

		 public void setUp(String data[])
		{
			try
			{
				length = Integer.parseInt(data[1]);
				name = new String[length];
				gender = new String[length];
				x = new int[length];
				y = new int[length];
				frame = new int[length];
				flip = new boolean[length];
				items = new Image[length][13];
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
			int xi = 0, yi = 0, namei = 0, genderi = 0, framei = 0, flipi = 0, chari = -1;
			try
			{
				for(int i = 2; i < data.length; i++)
				{
					try
					{
						if(data[i].equals("X"))
						{
							x[xi++] = Integer.parseInt(data[++i]);
						}
						if(data[i].equals("Y"))
						{
							y[yi++] = Integer.parseInt(data[++i]);
						}
						if(data[i].equals("Name"))
						{
							name[namei++] = data[++i];
							chari++;
						}
						if(data[i].equals("Gender"))
						{
							gender[genderi++] = data[++i];
						}
						if(data[i].equals("Frame"))
						{
							frame[framei++] = Integer.parseInt(data[++i]);
						}
						if(data[i].equals("Flip"))
						{
							if(data[++i].equals("1"))
							data[i] = "true";
							else
							data[i] = "false";
							flip[flipi++] = Boolean.parseBoolean(data[i]);
						}
						if(data[i].equals("SkinColor"))
						{
							try
							{
								items[chari][0] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/skin/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("HairStyle"))
						{
							try
							{
								items[chari][1] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/hair/" + data[++i] + "/" + data[i+2] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("EyeStyle"))
						{
							try
							{
								items[chari][2] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/eye/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Primary"))
						{
							try
							{
								items[chari][3] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/primary/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Secondary"))
						{
							try
							{
								items[chari][4] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/secondary/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Shoes"))
						{
							try
							{
								items[chari][5] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/shoes/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Bottom"))
						{
							try
							{
								items[chari][6] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/bottom/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Gloves"))
						{
							try
							{
								items[chari][7] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/gloves/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Top"))
						{
							try
							{
								items[chari][8] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/top/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Hat"))
						{
							try
							{
								items[chari][9] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/hat/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Bracer"))
						{
							try
							{
								items[chari][10] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/bracer/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Grieves"))
						{
							try
							{
								items[chari][11] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/grieves/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
						if(data[i].equals("Cape"))
						{
							try
							{
								items[chari][12] = tk.getImage(getURL("Graphics/Character/"+gender[chari]+"/cape/" + data[++i] + ".png"));
							}
							catch(Exception e)
							{
							}
							continue;
						}
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
				}
			}
			catch (Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public void paint(Graphics g)
		{
			for(int i = 0; i < length; i++)
			{
				try
				{
					for(int j = 0; j < items[i].length; j++)
					{
						try
						{
							drawFrame(items[i][j], g, x[i], y[i], 7, frame[i], 50, 50, flip[i]);
						}
						catch(Exception e)
						{
							if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
						}
					}
					g.setColor(Color.BLACK);
					g.fillRect(x[i]-(name[i].length()*3)+25-1, y[i]-11-1+65, 2*(name[i].length()*3)+name[i].length()+2, 12+2);
					g.setColor(Color.WHITE);
					g.fillRect(x[i]-(name[i].length()*3)+25, y[i]-11+65, 2*(name[i].length()*3)+name[i].length(), 12);
					g.setColor(Color.BLACK);
					g.drawString(name[i], x[i]+25-name[i].length()*3, y[i]+65);
				}
				catch(Exception e)
				{
					if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
				}
			}
		}
	}

	class Network implements Runnable
	{
		private Socket socket;
		private boolean started = false;
		private BufferedReader inStream;

		Network(String host, int port)
		{
			try
			{
				socket = new Socket(host, port);
				inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				started = true;

				new Thread(this).start();
			}
			catch (UnknownHostException e)
			{
				System.err.println("Don't know about host: "+host+".");
				started = false;
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
			catch (IOException e)
			{
				System.err.println("Couldn't get I/O for the connection to: "+host+".");
				started = false;
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
				started = false;
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		Network()
		{
		}

		public void send(String message)
		{
			try
			{
				new PrintStream(socket.getOutputStream()).println(message);
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public String[] between(String string, String search)
		{
			String[] a = new String[0];
			int count = 0;
			for(int i = 0; ; i++)
			{
				if(string.lastIndexOf(search) < count) break;
				a = add(a, string.substring(count, string.indexOf(search,count)));
				count = string.indexOf(search, count)+1;
			}
			return a;
		}

		private String[] add(String[] a, String add)
		{
			if(add.length() > 0)
				{
				String[] temp = a;
				a = new String[a.length+1];
				for(int i = 0; i < temp.length; i++)
				{
					a[i] = temp[i];
				}
				a[a.length-1] = add;
			}
			return a;
		}

		public void run()
		{
			String inputLine;
			try
			{
				//while(((inputLine = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine()) != null) && (started))
				while(((inputLine = inStream.readLine()) != null) && (started))
				{
					try
					{
						ProcessInput(inputLine);
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
				}
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
			started = false;
		}

		public void ProcessInput(String input)
		{
			String[] data;
			data = between(input,":");
			//chat.append(data[0]);
			if(data[0].equals("Login"))
			{
				Char = new String[1][3][16];
				int serv = 0;
				int chara = -1;

				for(int i = 0; i < Char.length; i++)
					for(int j = 0; j < Char[i].length; j++)
						Char[i][j][0] = "null";

				for(int i = 0; i < data.length; i++)
				{
					if(data[i].equals("Name"))
					{
						if(++chara>=3)
						{
							chara=0;
							serv++;
						}
						Char[serv][chara][0] = data[++i];
						continue;
					}
					if(data[i].equals("Server"))
					{
						Char[serv][chara][1] = data[++i];
						continue;
					}
					if(data[i].equals("Gender"))
					{
						Char[serv][chara][2] = data[++i];
						continue;
					}
					if(data[i].equals("SkinColor"))
					{
						Char[serv][chara][3] = "Graphics/Character/"+Char[serv][chara][2]+"/skin/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("HairStyle"))
					{
						Char[serv][chara][4] = "Graphics/Character/"+Char[serv][chara][2]+"/hair/" + data[++i] + "/" + data[i+2] + ".png";
						continue;
					}
					if(data[i].equals("EyeStyle"))
					{
						Char[serv][chara][5] = "Graphics/Character/"+Char[serv][chara][2]+"/eye/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Primary"))
					{
						Char[serv][chara][6] = "Graphics/Character/"+Char[serv][chara][2]+"/primary/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Secondary"))
					{
						Char[serv][chara][7] = "Graphics/Character/"+Char[serv][chara][2]+"/secondary/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Shoes"))
					{
						Char[serv][chara][8] = "Graphics/Character/"+Char[serv][chara][2]+"/shoes/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Bottom"))
					{
						Char[serv][chara][9] = "Graphics/Character/"+Char[serv][chara][2]+"/bottom/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Gloves"))
					{
						Char[serv][chara][10] = "Graphics/Character/"+Char[serv][chara][2]+"/gloves/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Top"))
					{
						Char[serv][chara][11] = "Graphics/Character/"+Char[serv][chara][2]+"/top/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Hat"))
					{
						Char[serv][chara][12] = "Graphics/Character/"+Char[serv][chara][2]+"/hat/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Bracer"))
					{
						Char[serv][chara][13] = "Graphics/Character/"+Char[serv][chara][2]+"/bracer/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Grieves"))
					{
						Char[serv][chara][14] = "Graphics/Character/"+Char[serv][chara][2]+"/grieves/" + data[++i] + ".png";
						continue;
					}
					if(data[i].equals("Cape"))
					{
						Char[serv][chara][15] = "Graphics/Character/"+Char[serv][chara][2]+"/cape/" + data[++i] + ".png";
						continue;
					}
				}
				String Servers[] = new String[Server.length];
				for(int i = 0; i < Server.length; i++)
				{
					Servers[i] = Server[i][0];
				}
				ServerList.setListData(Servers);
				ServerList.setSelectedIndex(0);
				ChannelList.setSelectedIndex(0);
				frameDeleteCharacter.setVisible(false);
				frameCreateCharacter.setVisible(false);
				frameLogin.setVisible(false);
				frameCreateAccount.setVisible(false);
				frameCharacters.setVisible(false);
				frameServers.setVisible(true);
				PasswordPasswordField.setText("");
				return;
			}
			if(data[0].equals("AccountCreationSuccess"))
			{
				frameCreateAccount.setVisible(false);
				frameLogin.setVisible(true);
				CreateAccountEmailTextField.setText("");
				CreateAccountNicknameTextField.setText("");
				CreateAccountPasswordPasswordField.setText("");
				return;
			}
			if(data[0].equals("AccountCreationFailure"))
			{
				return;
			}
			if(data[0].equals("Play"))
			{
				network = new Network(data[2],Integer.parseInt(data[3]));
				network.send("Play:" + data[1] + ":");
				hero.name = data[1];
				hero.x = Integer.parseInt(data[4]);
				hero.y = Integer.parseInt(data[5]);
				map.current = Integer.parseInt(data[6]);
				map.transport(Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));
				frameCharacters.setVisible(false);
				return;
			}
			if(data[0].equals("Location"))
			{
				for(int i = 1; i < data.length; i++)
				{
					if(data[i].equals("X"))
					{
						hero.x = Integer.parseInt(data[++i]);
						continue;
					}
					else if(data[i].equals("Y"))
					{
						hero.y = Integer.parseInt(data[++i]);
						continue;
					}
				}
				return;
			}
			if(data[0].equals("You"))
			{
				try
				{
					hero.setUpHero(data);
					canvas.repaint();
				}
				catch(Exception e)
				{
					if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
				}
				return;
			}
			if(data[0].equals("Others"))
			{
				others.setUp(data);
				canvas.repaint();
				return;
			}

			if(data[0].equals("Send"))
			{
				chat.append(input.substring(input.indexOf(data[1], 0), input.length())+"\n");
				chatSP.getVerticalScrollBar().setValue(chatSP.getVerticalScrollBar().getMaximum());
				//chatSP.getVerticalScrollBar().setValue(chatSP.getVerticalScrollBar().getValue() + (chatSP.getVerticalScrollBar().getMaximum() - chatSP.getVerticalScrollBar().getValue()));
			}

			if(data[0].equals("Navigator"))
			{
				nav.setUp(data);
			}

			if(data[0].equals("Inventory"))
			{
				inventory.setUp(data);
			}

			if(data[0].equals("GroundItems"))
			{
				grounditems.setUp(data);
			}

			if(data[0].equals("Equipment"))
			{
				equipment.setUp(data);
			}
		}
	}

	class GroundItems
	{
		int[] x, y;
		Image[] items;
		String[] itemsString, type;
		int count;
		Toolkit tk = Toolkit.getDefaultToolkit();

		public void setUp(String[] data)
		{
			count = Integer.parseInt(data[1]);
			items = new Image[count];
			itemsString = new String[count];
			type = new String[count];
			x = new int[count];
			y = new int[count];
			int itemscount=0, xcount=0, ycount=0,typecount=0;
			for(int i = 2; i < data.length; i++)
			{
				try
				{
					if(data[i].equals("Image"))
					{
						//items[itemscount++] = tk.getImage("Graphics/Item Icons/"+data[++i]+".png");
						itemsString[itemscount++] = data[++i];
					}
					if(data[i].equals("Type"))
					{
						type[typecount++] = data[++i];
					}
					if(data[i].equals("X"))
					{
						x[xcount++] = Integer.parseInt(data[++i]);
					}
					if(data[i].equals("Y"))
					{
						y[ycount++] = Integer.parseInt(data[++i]);
					}
				}
				catch(Exception e)
				{
					if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
				}
			}
			for(int i = 0; i < count; i++)
			{
				items[i] = tk.getImage("Graphics/Item Icons/" + type[i] + "/" + itemsString[i]+".png");
				//chat.append("Graphics/Item Icons/" + type[i] + "/" + itemsString[i]+".png\n");
			}
		}

		public void paint(Graphics g)
		{
			for(int i = 0; i < count; i++)
			{
				try
				{
					g.drawImage(items[i], x[i], y[i], desktop);
				}
				catch(Exception e)
				{
					if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
				}
			}
		}
	}

	class Inventory
	{
		int slots, slotsLength;
		int ID[] = {};
		ImageIcon[] items = {};
		String[] itemsStrings = {};
		int ReqLevel[] = {};
		String Type[] = {};
		int ReqStrength[] = {};
		int ReqDexterity[] = {};
		int ReqIntelegence[] = {};
		int AddStrength[] = {};
		int AddDexterity[] = {};
		int AddIntelegence[] = {};
		Toolkit tk = Toolkit.getDefaultToolkit();

		public void setUp(String data[])
		{
			//slots = Integer.parseInt(data[1]+40);
			//slots = Integer.parseInt("40");
			slotsLength = Integer.parseInt("40");
			ID = new int[slotsLength];
			items = new ImageIcon[slotsLength];
			itemsStrings = new String[slotsLength];
			ReqLevel = new int[slotsLength];
			Type = new String[slotsLength];
			ReqStrength = new int[slotsLength];
			ReqDexterity = new int[slotsLength];
			ReqIntelegence = new int[slotsLength];
			AddStrength = new int[slotsLength];
			AddDexterity = new int[slotsLength];
			AddIntelegence = new int[slotsLength];
			for(int i = 0; i < slotsLength; i++)
			{
				items[i] = new ImageIcon(tk.getImage("Graphics/Item Icons/null.png"));
				ID[i] = 0;
				itemsStrings[i] = "null";
				ReqLevel[i] = 0;
				Type[i] = "null";
				ReqStrength[i] = 0;
				ReqDexterity[i] = 0;
				ReqIntelegence[i] = 0;
			}
			for(int i = 1; i < data.length; i++)
			{
				if(data[i].equals("Slot"))
				{
					slots = Integer.parseInt(data[++i])-100;
				}
				if(data[i].equals("Image"))
				{
					itemsStrings[slots] =  data[++i];
				}
				if(data[i].equals("ID"))
				{
					ID[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("ReqLevel"))
				{
					ReqLevel[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("Type"))
				{
					Type[slots] = data[++i];
				}
				if(data[i].equals("ReqStrength"))
				{
					ReqStrength[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("ReqDexterity"))
				{
					ReqDexterity[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("ReqIntelegence"))
				{
					ReqIntelegence[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("AddStrength"))
				{
					AddStrength[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("AddDexterity"))
				{
					AddDexterity[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("AddIntelegence"))
				{
					AddIntelegence[slots] = Integer.parseInt(data[++i]);
				}
			}
			for(int i = 0; i < slotsLength; i++)
			{
				items[i] = new ImageIcon(tk.getImage("Graphics/Item Icons/" + Type[i] + "/" + itemsStrings[i]+".png"));
			}
			int i = JListInventory.getSelectedIndex();
			JListInventory.setListData(items);
			JListInventory.setSelectedIndex(i);
		}
	}

	class Equipment
	{
		int slots, slotsLength;
		int ID[] = {};
		ImageIcon[] items = {};
		String[] itemsStrings = {};
		int ReqLevel[] = {};
		String Type[] = {};
		int ReqStrength[] = {};
		int ReqDexterity[] = {};
		int ReqIntelegence[] = {};
		int AddStrength[] = {};
		int AddDexterity[] = {};
		int AddIntelegence[] = {};
		Toolkit tk = Toolkit.getDefaultToolkit();

		public void setUp(String data[])
		{
			//slots = Integer.parseInt("24");
			slotsLength = Integer.parseInt("48");
			ID = new int[slotsLength];
			items = new ImageIcon[slotsLength];
			itemsStrings = new String[slotsLength];
			ReqLevel = new int[slotsLength];
			Type = new String[slotsLength];
			ReqStrength = new int[slotsLength];
			ReqDexterity = new int[slotsLength];
			ReqIntelegence = new int[slotsLength];
			AddStrength = new int[slotsLength];
			AddDexterity = new int[slotsLength];
			AddIntelegence = new int[slotsLength];
			for(int i = 0; i < slotsLength; i++)
			{
				items[i] = new ImageIcon(tk.getImage("Graphics/Item Icons/null.png"));
				ID[i] = 0;
				itemsStrings[i] = "null";
				ReqLevel[i] = 0;
				Type[i] = "null";
				ReqStrength[i] = 0;
				ReqDexterity[i] = 0;
				ReqIntelegence[i] = 0;
			}
			for(int i = 1; i < data.length; i++)
			{
				if(data[i].equals("Slot"))
				{
					slots = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("Image"))
				{
					itemsStrings[slots] =  data[++i];
				}
				if(data[i].equals("ID"))
				{
					ID[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("ReqLevel"))
				{
					ReqLevel[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("Type"))
				{
					Type[slots] = data[++i];
				}
				if(data[i].equals("ReqStrength"))
				{
					ReqStrength[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("ReqDexterity"))
				{
					ReqDexterity[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("ReqIntelegence"))
				{
					ReqIntelegence[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("AddStrength"))
				{
					AddStrength[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("AddDexterity"))
				{
					AddDexterity[slots] = Integer.parseInt(data[++i]);
				}
				if(data[i].equals("AddIntelegence"))
				{
					AddIntelegence[slots] = Integer.parseInt(data[++i]);
				}
			}
			for(int i = 0; i < slotsLength; i++)
			{
				items[i] = new ImageIcon(tk.getImage("Graphics/Item Icons/" + Type[i] + "/" + itemsStrings[i]+".png"));
			}
			int i = JListEquipment.getSelectedIndex();
			JListEquipment.setListData(items);
			JListEquipment.setSelectedIndex(i);
		}
	}

	class CharacterList extends Container
	{
	    private Dimension dim;
	    private Graphics bufferGraphics;
	    private Image offscreen;
		private Toolkit tk = Toolkit.getDefaultToolkit();
		private Image images[] = new Image[13];
		private String name = "null";
		private int ListNumber = 0;

		CharacterList(int ListNumber, int height, int width)
		{
			this.ListNumber = ListNumber;
			setSize(height, width);
		}

		public void CreateList(String items[])
		{
			name = items[0];
			if(name.equals("null"))
			{
				PlayButton[ListNumber].setVisible(false);
				CreateDeleteButton[ListNumber].setText("Create");
			}
			else
			{
				PlayButton[ListNumber].setVisible(true);
				CreateDeleteButton[ListNumber].setText("Delete");
				for(int i = 3; i < images.length+3; i++)
				{
					try
					{
						images[i-3] = tk.getImage(getURL(items[i]));
						chat.append(items[i]+"\n");
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
				}
			}
		}

		public void SetUp(int height, int width)
		{
			try
			{
				setSize(height, width);
				dim = getSize();
				offscreen = createImage(dim.width,dim.height);
				bufferGraphics = offscreen.getGraphics();
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public void paint(Graphics g)
		{
			try
			{
				bufferGraphics.clearRect(0,0,100,100);
				if(!name.equals("null"))
				{
					for(int i = 0; i < images.length; i++)
					{
						try
						{
							drawFrame(images[i], bufferGraphics, 0, 0, 7, 4, 50, 50, false);
						}
						catch(Exception e)
						{
							if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
						}
					}

					//drawFrame(tk.getImage("Graphics/Character/male/bottom/basic.png"), bufferGraphics, 0, 0, 7, 4, 50, 50, false);

					bufferGraphics.drawString(name,12,62);
					g.drawImage(offscreen,0,0,100,100,desktop);
				}
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public void update(Graphics g)
		{
		}
	}


	static class Mouse
	{
		static int OutOfBounds = -800;
		static int button;
		static int ClickedX, ClickedY;
		static boolean dragged;
		static int EnteredX, EnteredY;
		static int ExitedX, ExitedY;
		static int PressedX, PressedY;
		static int ReleasedX, ReleasedY;
		static int DraggedX, DraggedY;
		static int MovedX, MovedY;
	}

	class Map
	{
		private int current = 0;
		private Room room[];

		public void transport(int x, int y, int map)
		{
			current = map;
			hero.x = x;
			hero.y = y;

			canvas.SetUp(room[current].sizex,room[current].sizey);
		}

		Map()
		{
			room = new Room[2];
				room[0] = new Room("test.png",800,600,5,2,2,0);

						room[0].ledge[2] = new Ledge(50,50,50,true,true);
						room[0].ledge[3] = new Ledge(99,50,52,true,true,false);
						room[0].ledge[4] = new Ledge(149,100,302,true,true);
						room[0].ledge[5] = new Ledge(450,100,50,true,true, true);
						room[0].ledge[6] = new Ledge(0,150,800,false,true);

						room[0].ladder[0] = new Ladder(51,50,100);
						room[0].ladder[1] = new Ladder(300,100,50);

						room[0].door[0] = new Door(350,100,10,false,0,300,30);
						room[0].door[1] = new Door(75,50,10,false,1,100,20);

						//room[0].wall[0] = new Wall(150,101,50);

				room[1] = new Room("test2.png",900,900,2,1,1,0);

						room[1].ledge[2] = new Ledge(0,300,900,false,true);
						room[1].ledge[3] = new Ledge(90,240,180,false,false);

						room[1].ladder[0] = new Ladder(800,200,699);

						room[1].door[0] = new Door(10,300,10,false, 0, 300, 30);

		}

		public void paint(Graphics g)
		{
			if(Global.debug)
			{
				for(int i = 0; i < room[current].ledge.length; i++)
				{
					room[current].ledge[i].paint(g);
				}
				for(int i = 0; i < room[current].ladder.length; i++)
				{
					room[current].ladder[i].paint(g);
				}
				for(int i = 0; i < room[current].door.length; i++)
				{
					room[current].door[i].paint(g);
				}
				for(int i = 0; i < room[current].wall.length; i++)
				{
					room[current].wall[i].paint(g);
				}
			}
		}

		class Room
		{
			private Ledge ledge[];
			private Ladder ladder[];
			private Door door[];
			private Wall wall[];
			private int sizex, sizey;
			private Image background;
			private Toolkit tk = Toolkit.getDefaultToolkit();

			Room(String background, int sizex, int sizey, int ledges, int ladders, int doors, int walls)
			{
				this.background = tk.getImage("Graphics/Backgrounds/"+background);
				this.sizex = sizex;
				this.sizey = sizey;

				this.ledge = new Ledge[ledges+2];
				this.ladder = new Ladder[ladders];
				this.door = new Door[doors];
				this.wall = new Wall[walls+2];

				wall[0] = new Wall(0,0,sizey);
				wall[1] = new Wall(sizex-1,0,sizey);
				ledge[0] = new Ledge(0,0,sizex,false,false);
				ledge[1] = new Ledge(0,sizey-1,sizex,false,false);
			}
		}

		class Wall
		{
			private int x, y, height;
			private int xs[];
			private int ys[];

			Wall(int x, int y, int height)
			{
				this.x = x;
				this.y = y;
				this.height = height;

				int varx[] = {x,x+1,x+1,x};
				int vary[] = {y,y,y+height,y+height};
				this.xs = varx;
				this.ys = vary;
			}

			public void paint(Graphics g)
			{
				g.setColor(Color.RED);
				g.fillPolygon(xs,ys,4);
			}

			public boolean range(int y)
			{
				if(y >= this.y && y <= this.y+this.height)
				return true;
				return false;
			}
		}

		class Ledge
		{
			private int x, y, width;
			private boolean slope = false, positive = false, fall, jump = false;
			private int xs[];
			private int ys[];

			Ledge(int x, int y, int width, boolean fall, boolean jump, boolean positive)
			{
				this.x = x;
				this.y = y;
				this.width = width;
				this.slope = true;
				this.fall = fall;
				this.jump = jump;
				this.positive = positive;

				if(positive)
				{
					int varx[] = {x,x+width,x+width,x};
					int vary[] = {y,y-width,y-width+1,y+1};
					this.xs = varx;
					this.ys = vary;
				}

				if(!positive)
				{
					int varx[] = {x,x+width,x+width,x};
					int vary[] = {y,y+width,y+width+1,y+1};
					this.xs = varx;
					this.ys = vary;
				}
			}

			Ledge(int x, int y, int width, boolean fall, boolean jump)
			{
				this.x = x;
				this.y = y;
				this.width = width;
				this.fall = fall;
				this.jump = jump;
				this.slope = false;

				int varx[] = {x,x+width,x+width,x};
				int vary[] = {y,y,y+1,y+1};
				this.xs = varx;
				this.ys = vary;
			}

			public int function(int x)
			{
				int y = 0;
				x = this.x - x;
				if(slope)
				{
					if(positive)
					{
						y = this.y + x;
					}
					if(!positive)
					{
						y = this.y - x;
					}
				}
				if(!slope)
				{
					y = this.y;
				}
				return y;
			}

			public boolean domain(int x)
			{
				if(x <= this.x || x >= this.x+this.width)
				return false;
				return true;
			}

			public void paint(Graphics g)
			{
				g.setColor(Color.BLACK);
				g.fillPolygon(xs,ys,4);
			}
		}

		class Ladder
		{
			private int x, y, height;
			private int xs[];
			private int ys[];

			Ladder(int x, int y, int height)
			{
				this.x = x;
				this.y = y;
				this.height = height;

				int varx[] = {x,x+1,x+1,x};
				int vary[] = {y,y,y+height,y+height};
				this.xs = varx;
				this.ys = vary;
			}

			public void paint(Graphics g)
			{
				g.setColor(Color.GREEN);
				g.fillPolygon(xs,ys,4);
			}

			public boolean range(int y)
			{
				if(y < this.y)
				return false;
				if(y > this.y+this.height)
				return false;
				return true;
			}
		}

		class Door
		{
			private int x, y, width;
			private boolean auto;
			private int xs[];
			private int ys[];
			private int map, playerx, playery;

			Door()
			{
			}

			Door(int x, int y, int width, boolean auto, int map, int playerx, int playery)
			{
				this.x = x;
				this.y = y;
				this.width = width;
				this.auto = auto;
				this.map = map;
				this.playerx = playerx;
				this.playery = playery;

				int varx[] = {x,x+width,x+width,x};
				int vary[] = {y,y,y+1,y+1};
				this.xs = varx;
				this.ys = vary;
			}

			public void transport()
			{
				current = map;
				hero.x = playerx;
				hero.y = playery;

				canvas.SetUp(room[current].sizex,room[current].sizey);
			}

			public boolean domain(int x)
			{
				if(x < this.x)
				return false;
				if(x > this.x+this.width)
				return false;
				return true;
			}

			public void paint(Graphics g)
			{
				g.setColor(Color.ORANGE);
				g.fillPolygon(xs,ys,4);
			}
		}
	}

	class Hero
	{
		private int x=400, y, frame=4, frameDelay, jumpHeight = 0;
		private boolean flip, jump = false, climbing = false;
		private String name = "", gender;
		private Image[] items;
		private Toolkit tk = Toolkit.getDefaultToolkit();

		void setUpHero(String data[])
		{
			try
			{
				items = new Image[13];
				for(int i = 0; i < data.length; i++)
				{
					if(data[i].equals("Name"))
					{
						name = data[++i];
						continue;
					}
					if(data[i].equals("Gender"))
					{
						gender = data[++i];
						continue;
					}
					if(data[i].equals("Gender"))
					{
						gender = data[++i];
						continue;
					}
					if(data[i].equals("SkinColor"))
					{
						try
						{
							items[0] = tk.getImage(getURL("Graphics/Character/"+gender+"/skin/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("HairStyle"))
					{
						try
						{
							items[1] = tk.getImage(getURL("Graphics/Character/"+gender+"/hair/" + data[++i] + "/" + data[i+2] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("EyeStyle"))
					{
						try
						{
							items[2] = tk.getImage(getURL("Graphics/Character/"+gender+"/eye/" + data[++i] + "/" + data[i+2] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Primary"))
					{
						try
						{
							items[3] = tk.getImage(getURL("Graphics/Character/"+gender+"/primary/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Secondary"))
					{
						try
						{
							items[4] = tk.getImage(getURL("Graphics/Character/"+gender+"/secondary/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Shoes"))
					{
						try
						{
							items[5] = tk.getImage(getURL("Graphics/Character/"+gender+"/shoes/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Bottom"))
					{
						try
						{
							items[6] = tk.getImage(getURL("Graphics/Character/"+gender+"/bottom/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Gloves"))
					{
						try
						{
							items[7] = tk.getImage(getURL("Graphics/Character/"+gender+"/gloves/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Top"))
					{
						try
						{
							items[8] = tk.getImage(getURL("Graphics/Character/"+gender+"/top/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Hat"))
					{
						try
						{
							items[9] = tk.getImage(getURL("Graphics/Character/"+gender+"/hat/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Bracer"))
					{
						try
						{
							items[10] = tk.getImage(getURL("Graphics/Character/"+gender+"/bracer/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Grieves"))
					{
						try
						{
							items[11] = tk.getImage(getURL("Graphics/Character/"+gender+"/grieves/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
					if(data[i].equals("Cape"))
					{
						try
						{
							items[12] = tk.getImage(getURL("Graphics/Character/"+gender+"/cape/" + data[++i] + ".png"));
						}
						catch(Exception e)
						{
						}
						continue;
					}
				}
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public void paint(Graphics g)
		{
			try
			{
				for(int i = 0; i < items.length; i++)
				{
					try
					{
						drawFrame(items[i], g, x, y, 7, frame, 50, 50, flip);
					}
					catch(Exception e)
					{
						if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
					}
				}
				g.setColor(Color.BLACK);
				g.fillRect(x-(name.length()*3)+25-1, y-11-1+65, 2*(name.length()*3)+name.length()+2, 12+2);
				g.setColor(Color.WHITE);
				g.fillRect(x-(name.length()*3)+25, y-11+65, 2*(name.length()*3)+name.length(), 12);
				g.setColor(Color.BLACK);
				g.drawString(name, x+25-name.length()*3, y+65);
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}
	}

	class BasicCharacter extends Container
	{
		private Dimension dim;
		private Graphics bufferGraphics;
		private Image offscreen;
		String gender = "";

		public void setUp()
		{
			try
			{
				setSize(100,100);
				dim = new Dimension(100,100);
				offscreen = createImage(dim.width,dim.height);
				bufferGraphics = offscreen.getGraphics();
			}
			catch(Exception e)
			{
				if(Global.debug)try{e.printStackTrace(new PrintStream(login.socket.getOutputStream()));}catch(Exception E){}
			}
		}

		public void paint(Graphics g)
		{
			Image[] image = new Image[5];

			switch(CreateCharacterGenderComboBox.getSelectedIndex())
			{
				case 0: gender="male"; break;
				case 1: gender="female"; break;
			}

			folder = "Graphics/Character/"+gender+"/skin/";
			switch(CreateCharacterSkinColorComboBox.getSelectedIndex())
			{
				case 0: image[0] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
				default: image[0] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
			}

			folder = "Graphics/Character/"+gender+"/top/";
			switch(CreateCharacterTopComboBox.getSelectedIndex())
			{
				case 0: image[1] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
				default: image[1] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
			}

			folder = "Graphics/Character/"+gender+"/bottom/";
			switch(CreateCharacterBottomComboBox.getSelectedIndex())
			{
				case 0: image[2] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
				default: image[2] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
			}

			folder = "Graphics/Character/"+gender+"/shoes/";
			switch(CreateCharacterShoesComboBox.getSelectedIndex())
			{
				case 0: image[3] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
				default: image[3] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
			}

			folder = "Graphics/Character/weapons/";
			switch(CreateCharacterWeaponsComboBox.getSelectedIndex())
			{
				case 0: image[4] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
				default: image[4] = Toolkit.getDefaultToolkit().getImage(folder+"basic.png"); break;
			}
			for(int i = 0; i < image.length; i++)
			{
				try
				{
					bufferGraphics.drawImage(image[i], 0, 0, this);
				}
				catch(Exception e)
				{
				}
			}
			//bufferGraphics.drawImage(Toolkit.getDefaultToolkit().getImage("Graphics/Character/Male/Bottom/basic.png"), 0, 0, this);
			g.drawImage(offscreen, 0, 0, this);//bufferGraphics
		}

		public void update(Graphics g)
		{
		}
	}

}

class Global
{
	static final boolean debug = true;
	static final int ScreenWidth = 800, ScreenHeight = 600;
}