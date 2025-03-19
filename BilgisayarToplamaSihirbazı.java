package donemProjesi;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class BilgisayarToplamaSihirbazı {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTextField namefield2;
    private JPasswordField passwordfield;
    private JTextField türField;
    private JTextField modelField;
    private JTextField fiyatField;
    private JTextField ozellikField;
    
    static ComputerBuilder pc = new ComputerBuilder();
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                BilgisayarToplamaSihirbazı pencere = new BilgisayarToplamaSihirbazı();
                pencere.frame.setVisible(true);

                pencere.frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // Dosyaya bileşenleri kaydet
                        pc.saveComponentsToFile("components.txt");
                        System.out.println("Uygulama kapanırken bileşenler kaydedildi.");
                        // Programı gerçekten kapat
                        System.exit(0);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public BilgisayarToplamaSihirbazı() {
        initialize();
    }
    
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Bilgisayar Toplama Sihirbazı");
        frame.setBounds(100, 100, 1175, 861);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new BackgroundPanel("duvarkağıdı.jpg");
        mainPanel.setLayout(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);
        cardPanel.setBounds(50, 50, 1350, 800);

        addCards();

        mainPanel.add(cardPanel);

        frame.getContentPane().add(mainPanel);
    }
    
    private void addCards() {
    	BagliListeKuyruk queue = new BagliListeKuyruk();
    	pc.loadComponentsFromFile("components.txt"); // Dosyadan bileşenleri yükle
    	
        JPanel panel1 = new JPanel(null);
        panel1.setBorder(null);
        panel1.setOpaque(false);

        JLabel label1 = new JLabel("İsim:");
        label1.setForeground(new Color(255, 255, 255));
        label1.setFont(new Font("Cambria", Font.PLAIN, 24));
        label1.setBounds(50, 198, 100, 30);
        panel1.add(label1);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nameField.setForeground(new Color(255, 255, 255));
        nameField.setBackground(new Color(0, 0, 0));
        nameField.setBounds(227, 191, 232, 44);
        panel1.add(nameField);

        JLabel label2 = new JLabel("Soyisim:");
        label2.setForeground(new Color(255, 255, 255));
        label2.setFont(new Font("Cambria", Font.PLAIN, 24));
        label2.setBounds(50, 286, 100, 30);
        panel1.add(label2);

        JTextField surnameField = new JTextField();
        surnameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        surnameField.setForeground(new Color(255, 255, 255));
        surnameField.setBackground(new Color(0, 0, 0));
        surnameField.setBounds(227, 279, 232, 44);
        panel1.add(surnameField);

        JLabel lblNewLabel = new JLabel("Bütçe(₺):");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblNewLabel.setBounds(50, 374, 100, 30);
        panel1.add(lblNewLabel);

        JSlider slider = new JSlider(10000, 100000, 55000);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20000);
        slider.setMinorTickSpacing(10000);
        slider.setForeground(Color.WHITE);
        slider.setBackground(Color.BLACK);
        slider.setBounds(160, 360, 400, 44);
        panel1.add(slider);

        // Kart 2: Sonuç ekranı
        JPanel panel2 = new JPanel(null);
        panel2.setOpaque(false);

        JButton backButton2 = new JButton("Geri");
        backButton2.setForeground(new Color(255, 255, 255));
        backButton2.setBackground(new Color(0, 0, 0));
        backButton2.setBounds(10, 10, 100, 30);
        backButton2.addActionListener(e -> cardLayout.show(cardPanel, "Kart1"));
        panel2.add(backButton2);
        
        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        
        cardPanel.add(panel1, "Kart1");
        
        JButton buton1 = new JButton("Sistem Öner");
        buton1.setForeground(new Color(255, 255, 255));
        buton1.setBackground(new Color(0, 0, 0));
        buton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (nameField.getText().isEmpty() || surnameField.getText().isEmpty()) {
        			JOptionPane.showMessageDialog(buton1, "Lütfen bilgilerinizi giriniz!");
        		}else if(slider.getValue() == 55000) {
        			JOptionPane.showMessageDialog(buton1, "Lütfen bütçenizi seçiniz!");
        		}else {
        			cardLayout.show(cardPanel, "Kart2");
        		}
        	}
        });
        buton1.setFont(new Font("Cambria", Font.BOLD, 12));
        buton1.setBounds(305, 461, 154, 56);
        panel1.add(buton1);
        
        JButton btnNewButton = new JButton("Admin Paneli");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(cardPanel, "Kart3");
        		
        	}
        });
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
        btnNewButton.setBounds(50, 62, 154, 56);
        panel1.add(btnNewButton);
        
        
        
        JLabel sistemOneriText = new JLabel("New label");
        sistemOneriText.setForeground(new Color(255, 255, 255));
        sistemOneriText.setBackground(new Color(0, 0, 0));
        sistemOneriText.setFont(new Font("Cambria", Font.BOLD, 22));
        sistemOneriText.setBounds(10, 257, 836, 477);
        panel2.add(sistemOneriText);
        
        JLabel fiyatBelirtici = new JLabel("New label");
        fiyatBelirtici.setForeground(new Color(255, 255, 255));
        fiyatBelirtici.setBackground(new Color(0, 0, 0));
        fiyatBelirtici.setFont(new Font("Cambria", Font.BOLD, 28));
        fiyatBelirtici.setBounds(10, 77, 511, 70);
        panel2.add(fiyatBelirtici);
        cardPanel.add(panel3, "Kart3");
        panel3.setLayout(null);
        
        slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int butce = slider.getValue();
				fiyatBelirtici.setText("Bütçe: " + butce + " TL");
				if(butce < 20000) {
					sistemOneriText.setText("<html>Önerilen bilgisayar:<br>İşlemci: AMD Ryzen 5 5600X <br>Anakart: MSI B550M PRO <br>Ekran Kartı: NVIDIA Geforce GTX 1650 Super<br>RAM: Corsair Vengeance LPX 16GB (2x8GB) DDR4 3200MHz<br>SSD: Samsung 970 EVO Plus 500GB NVMe M.2<br>PSU: Cooler Master MWE 650W 80+ Bronze" );
				}else if(30000 < butce && butce < 40000) {
					sistemOneriText.setText("<html>Önerilen bilgisayar:<br>İşlemci: AMD Ryzen 7 7800X<br>Anakart: MSI MAG X670 TOMAHAWK WiFi<br>Ekran Kartı: NVIDIA GeForce RTX 4060 Ti<br>RAM: Corsair Vengeance RGB Pro 32GB (2x16GB) DDR5 6000MHz<br>SSD: Samsung 980 Pro 1TB NVMe M.2 SSD<br>PSU: Corsair RM850x 850W 80+ Gold");
				}else if(20000 < butce && butce < 30000) {
					sistemOneriText.setText("<html>Önerilen bilgisayar:<br>İşlemci: AMD Ryzen 5 7600X<br>Anakart: ASUS TUF Gaming B650-PLUS WiFi<br>Ekran Kartı: NVIDIA GeForce RTX 3060 Ti<br>RAM: Kingston Fury Beast 32GB (2x16GB) DDR5 5200MHz<br>SSD: Western Digital Black SN770 1TB NVMe M.2 SSD<br>PSU: Seasonic Focus GX-750 750W 80+ Gold");
				}else if(40000 < butce && butce < 60000) {
					sistemOneriText.setText("<html>Önerilen bilgisayar:<br>İşlemci: Intel Core i7-13700KF<br>Anakart: ASUS ROG STRIX Z690-A Gaming WiFi DDR5<br>Ekran Kartı: NVIDIA GeForce RTX 4070 Ti<br>RAM: Corsair Vengeance RGB DDR5 32GB (2x16GB) 6000MHz<br>SSD: Samsung 980 PRO 2TB PCIe 4.0 NVMe SSD<br>PSU: Corsair RM850e 850W 80+");
				}else if(60000 < butce && butce < 80000) {
					sistemOneriText.setText("<html>Önerilen bilgisayar:<br>İşlemci: Intel Core i9-13900K<br>Anakart: ASUS ROG STRIX Z790-E Gaming WiFi DDR5<br>Ekran Kartı: AMD Radeon RX 7900 XTX (Sapphire Nitro+ veya ASUS TUF Gaming)<br>RAM: G.SKILL Trident Z5 RGB DDR5 64GB (2x32GB) 6000MHz<br>SSD: Samsung 990 PRO 2TB PCIe 4.0 NVMe SSD<br>PSU: Corsair HX1000i 1000W 80+ Platinum Fully Modular");
				}else if(80000 < butce && butce < 100000) {
					sistemOneriText.setText("<html>Önerilen bilgisayar:<br>İşlemci: AMD Ryzen 9 7950X<br>Anakart: ASUS ROG Crosshair X670E Hero<br>Ekran Kartı: NVIDIA GeForce RTX 4090 (MSI Gaming X Trio veya ASUS TUF)<br>RAM: Corsair Dominator Platinum RGB DDR5 64GB (2x32GB) 6200MHz<br>SSD: Corsair MP700 4TB PCIe 5.0 NVMe SSD<br>PSU: Seasonic PRIME TX-1000W 80+ Titanium Fully Modular");
				}
			}
		});
        
        JLabel label7 = new JLabel("Kullanıcı Adı:");
        label7.setForeground(new Color(255, 255, 255));
        label7.setBackground(new Color(0, 0, 0));
        label7.setFont(new Font("Cambria", Font.BOLD, 24));
        label7.setBounds(86, 322, 159, 32);
        panel3.add(label7);
        
        JLabel label8 = new JLabel("Şifre:");
        label8.setForeground(new Color(255, 255, 255));
        label8.setBackground(new Color(0, 0, 0));
        label8.setFont(new Font("Cambria", Font.BOLD, 24));
        label8.setBounds(86, 383, 145, 42);
        panel3.add(label8);
        
        namefield2 = new JTextField();
        namefield2.setForeground(new Color(255, 255, 255));
        namefield2.setBackground(new Color(0, 0, 0));
        namefield2.setFont(new Font("Cambria", Font.BOLD, 16));
        namefield2.setBounds(241, 326, 177, 31);
        panel3.add(namefield2);
        namefield2.setColumns(10);
        
        passwordfield = new JPasswordField();
        passwordfield.setBackground(new Color(0, 0, 0));
        passwordfield.setForeground(new Color(255, 255, 255));
        passwordfield.setFont(new Font("Cambria", Font.BOLD, 16));
        passwordfield.setBounds(241, 391, 177, 32);
        panel3.add(passwordfield);
        
        JLabel lblNewLabel_3 = new JLabel("ADMİN PANELİ");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 30));
        lblNewLabel_3.setBounds(86, 69, 332, 57);
        panel3.add(lblNewLabel_3);
        
        JPanel panel4 = new JPanel();
        panel4.setOpaque(false);
        cardPanel.add(panel4, "Kart 4");
        panel4.setLayout(null);
        
        JButton btnNewButton_2 = new JButton("Geri");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(cardPanel, "Kart3");
        	}
        });
        btnNewButton_2.setBackground(new Color(0, 0, 0));
        btnNewButton_2.setForeground(new Color(255, 255, 255));
        btnNewButton_2.setFont(new Font("Cambria", Font.BOLD, 10));
        btnNewButton_2.setBounds(10, 10, 100, 34);
        panel4.add(btnNewButton_2);
        
        JLabel lblNewLabel_4 = new JLabel("Admin Paneli");
        lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD, 28));
        lblNewLabel_4.setBackground(new Color(0, 0, 0));
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setBounds(328, -1, 271, 57);
        panel4.add(lblNewLabel_4);
        
        JButton btnbileşenEkle = new JButton("Bileşen Ekle");
        btnbileşenEkle.setForeground(Color.WHITE);
        btnbileşenEkle.setFont(new Font("Cambria", Font.BOLD, 16));
        btnbileşenEkle.setBackground(Color.BLACK);
        btnbileşenEkle.setBounds(1000, 483, 158, 34);
        panel4.add(btnbileşenEkle);
        
        JComboBox<String> tum_bilesenler = new JComboBox<>(new String[]{});
        tum_bilesenler.setSelectedIndex(-1);
        tum_bilesenler.setForeground(Color.WHITE);
        tum_bilesenler.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tum_bilesenler.setBackground(Color.BLACK);
        tum_bilesenler.setBounds(88, 308, 337, 30);
        panel4.add(tum_bilesenler);
        
        String tum_bilesenlerr = pc.displayComponents();
        String[] sattırlar = tum_bilesenlerr.split("\n");
        for (String satır : sattırlar) {
            if (!satır.isEmpty()) {
            	tum_bilesenler.addItem(satır);
            }
        }
        tum_bilesenler.setSelectedIndex(-1);
        
        JLabel lblNewLabel_4_2 = new JLabel("Bileşen Sil");
        lblNewLabel_4_2.setForeground(Color.WHITE);
        lblNewLabel_4_2.setFont(new Font("Cambria", Font.BOLD, 22));
        lblNewLabel_4_2.setBackground(Color.BLACK);
        lblNewLabel_4_2.setBounds(88, 251, 271, 57);
        panel4.add(lblNewLabel_4_2);
        
        JButton btnNewButton_2_1_2_1 = new JButton("Sil");
        btnNewButton_2_1_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedComponent = (String) tum_bilesenler.getSelectedItem();
                if (selectedComponent != null && !selectedComponent.isEmpty()) {
                    try {
                        String[] componentParts = selectedComponent.split(":");
                        String componentDetails = componentParts[1].trim();
                        String[] detailsParts = componentDetails.split(","); 
                        String componentName = detailsParts[0].trim();

                        pc.removeComponent(componentName);

                        int silinecek_indeks = tum_bilesenler.getSelectedIndex();
                        if (silinecek_indeks >= 0 && silinecek_indeks < tum_bilesenler.getItemCount()) {
                            tum_bilesenler.removeItemAt(silinecek_indeks); 
                        } else {
                            JOptionPane.showMessageDialog(btnNewButton_2_1_2_1, "Geçersiz indeks!");
                        }

                        JOptionPane.showMessageDialog(btnNewButton_2_1_2_1, "Bileşen başarıyla silindi.");
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(btnNewButton_2_1_2_1, "Bir hata oluştu: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(btnNewButton_2_1_2_1, "Lütfen silinecek bir bileşen seçin.");                    
                }
            }
        });
        btnNewButton_2_1_2_1.setForeground(Color.WHITE);
        btnNewButton_2_1_2_1.setFont(new Font("Cambria", Font.BOLD, 16));
        btnNewButton_2_1_2_1.setBackground(Color.BLACK);
        btnNewButton_2_1_2_1.setBounds(450, 306, 92, 34);
        panel4.add(btnNewButton_2_1_2_1);
        
        JLabel lblNewLabel_4_1 = new JLabel("Lütfen Yapmak İstediğiniz İşlemi Seçiniz");
        lblNewLabel_4_1.setForeground(Color.WHITE);
        lblNewLabel_4_1.setFont(new Font("Cambria", Font.BOLD, 20));
        lblNewLabel_4_1.setBackground(Color.BLACK);
        lblNewLabel_4_1.setBounds(88, 91, 400, 57);
        panel4.add(lblNewLabel_4_1);
        
        btnbileşenEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = türField.getText();
                String name = modelField.getText();
                String priceText = fiyatField.getText();
                String attributesText = ozellikField.getText();
                double price = Double.parseDouble(priceText); 
                String[] attributes = attributesText.split(","); 
                pc.addComponent(type, name, price, attributes); 

                if (pc.sayac == 1) {
                    JOptionPane.showMessageDialog(btnbileşenEkle, "Bileşen başarıyla eklendi");
                    String Bileşenler = pc.displayComponents();

                    // ComboBox'ı güncelle
                    tum_bilesenler.removeAllItems();
                    String[] sattırlar = Bileşenler.split("\n");
                    for (String satır : sattırlar) {
                        if (!satır.isEmpty()) {
                            tum_bilesenler.addItem(satır); 
                        }
                    }
                    tum_bilesenler.setSelectedIndex(-1);
                } else if (pc.sayac == 2) {
                    JOptionPane.showMessageDialog(btnbileşenEkle, "Bileşen eklem başarısız oldu");
                } else if (pc.sayac == 3) {
                    JOptionPane.showMessageDialog(btnbileşenEkle, "Bileşenin türünü doğru giriniz!");
                }
            }
        });
        
        JButton btnNewButton_2_1 = new JButton("Bileşen Sorgula");
        btnNewButton_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bileşenler = pc.displayComponents(); 
                
                JTextArea textArea = new JTextArea(20, 50); // 20 satır, 50 sütun
                textArea.setText(bileşenler);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);

                // JScrollPane ile textArea'yı sar
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(
                    null,
                    scrollPane,
                    "Bileşenler",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        
        btnNewButton_2_1.setForeground(Color.WHITE);
        btnNewButton_2_1.setFont(new Font("Cambria", Font.BOLD, 16));
        btnNewButton_2_1.setBackground(Color.BLACK);
        btnNewButton_2_1.setBounds(88, 184, 172, 34);
        panel4.add(btnNewButton_2_1);
        
        JLabel label_deneme = new JLabel("");
        label_deneme.setForeground(Color.WHITE);
        label_deneme.setFont(new Font("Cambria", Font.BOLD, 28));
        label_deneme.setBackground(Color.BLACK);
        label_deneme.setBounds(451, 209, 481, 179);
        panel4.add(label_deneme);
        
        türField = new JTextField();
        türField.setFont(new Font("Cambria", Font.BOLD, 14));
        türField.setForeground(new Color(255, 255, 255));
        türField.setBackground(new Color(0, 0, 0));
        türField.setBounds(88, 484, 158, 34);
        panel4.add(türField);
        türField.setColumns(10);
        
        modelField = new JTextField();
        modelField.setForeground(Color.WHITE);
        modelField.setFont(new Font("Cambria", Font.BOLD, 14));
        modelField.setColumns(10);
        modelField.setBackground(Color.BLACK);
        modelField.setBounds(256, 483, 158, 34);
        panel4.add(modelField);
        
        fiyatField = new JTextField();
        fiyatField.setForeground(Color.WHITE);
        fiyatField.setFont(new Font("Cambria", Font.BOLD, 14));
        fiyatField.setColumns(10);
        fiyatField.setBackground(Color.BLACK);
        fiyatField.setBounds(425, 484, 158, 34);
        panel4.add(fiyatField);
        
        ozellikField = new JTextField();
        ozellikField.setForeground(Color.WHITE);
        ozellikField.setFont(new Font("Cambria", Font.BOLD, 14));
        ozellikField.setColumns(10);
        ozellikField.setBackground(Color.BLACK);
        ozellikField.setBounds(593, 484, 397, 34);
        panel4.add(ozellikField);
        
        JLabel lblNewLabel_4_2_1 = new JLabel("Tür");
        lblNewLabel_4_2_1.setForeground(Color.WHITE);
        lblNewLabel_4_2_1.setFont(new Font("Cambria", Font.BOLD, 16));
        lblNewLabel_4_2_1.setBackground(Color.BLACK);
        lblNewLabel_4_2_1.setBounds(148, 440, 92, 34);
        panel4.add(lblNewLabel_4_2_1);
        
        JLabel lblNewLabel_4_2_1_1 = new JLabel("Model");
        lblNewLabel_4_2_1_1.setForeground(Color.WHITE);
        lblNewLabel_4_2_1_1.setFont(new Font("Cambria", Font.BOLD, 16));
        lblNewLabel_4_2_1_1.setBackground(Color.BLACK);
        lblNewLabel_4_2_1_1.setBounds(306, 440, 92, 34);
        panel4.add(lblNewLabel_4_2_1_1);
        
        JLabel lblNewLabel_4_2_1_2 = new JLabel("Fiyat");
        lblNewLabel_4_2_1_2.setForeground(Color.WHITE);
        lblNewLabel_4_2_1_2.setFont(new Font("Cambria", Font.BOLD, 16));
        lblNewLabel_4_2_1_2.setBackground(Color.BLACK);
        lblNewLabel_4_2_1_2.setBounds(475, 440, 92, 34);
        panel4.add(lblNewLabel_4_2_1_2);
        
        JLabel lblNewLabel_4_2_1_3 = new JLabel("Diğer Özellikler");
        lblNewLabel_4_2_1_3.setForeground(Color.WHITE);
        lblNewLabel_4_2_1_3.setFont(new Font("Cambria", Font.BOLD, 16));
        lblNewLabel_4_2_1_3.setBackground(Color.BLACK);
        lblNewLabel_4_2_1_3.setBounds(743, 440, 141, 34);
        panel4.add(lblNewLabel_4_2_1_3);
        
        JLabel lblNewLabel_5 = new JLabel("<html>LÜTFEN BİLEŞENLERİ AŞAĞIDAKİ ÖRNEK FORMATLARA UYGUN OLACAK ŞEKİLDE EKLEMEYE ÖZEN GÖSTERİNİZ<br>İşlemci -> Tür: Processor, Model: İntel Core i5 13500H, Fiyat: 2000, Diğer Özellikler: AM5(Soket tipi) <br>Anakart -> Tür: Motherboard, Model: MSI B450, Fiyat: 2000, Diğer Özellikler: Am4, DDR4 (hem soket tipi hem de ddr türü, aralarında virgül olacak)<br>Ekran Kartı -> Tür: GPU, Model: RTX 4090 Super, Fiyat: 100000, Diğer Özellikler: (boş bırakılacak)<br>RAM -> Tür: RAM, Model: G-Skill Ripjaws 2x8, Fiyat: 5000, Diğer Özellikler: DDR4<br>SSD -> Tür: SSD, Model: Samsung Evo 980, Fiyat: 9000, Diğer Özellikler: 480(Kaç gb olduğu)<br>PSU -> Tür: PSU, Model: Zalman BeQuiet, Fiyat: 3000, Diğer Özellikler: 600(Watt değeri)</html>");
        lblNewLabel_5.setForeground(new Color(255, 255, 255));
        lblNewLabel_5.setBackground(new Color(0, 0, 0));
        lblNewLabel_5.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(88, 550, 1262, 216);
        panel4.add(lblNewLabel_5);
        
                
        JButton buton5 = new JButton("Giriş");
        buton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (namefield2.getText().isEmpty() || new String(passwordfield.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(buton5, "Lütfen bilgilerinizi giriniz");
                } else {
                    if (namefield2.getText().equals("admin") && new String(passwordfield.getPassword()).equals("admin123")) {
                        cardLayout.show(cardPanel, "Kart 4");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hatalı giriş");
                    }
                }
            }
        });
        
        buton5.setFont(new Font("Cambria", Font.BOLD, 10));
        buton5.setForeground(new Color(255, 255, 255));
        buton5.setBackground(new Color(0, 0, 0));
        buton5.setBounds(314, 458, 104, 32);
        panel3.add(buton5);
        
        JButton btnNewButton_1 = new JButton("Geri");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(cardPanel, "Kart1");
        	}
        });
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(0, 0, 0));
        btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 10));
        btnNewButton_1.setBounds(10, 10, 91, 32);
        panel3.add(btnNewButton_1);
        
        JPanel panel5 = new JPanel();
        panel5.setOpaque(false);
        cardPanel.add(panel5, "Kart 5");
        
        JButton buton6 = new JButton("Geri");
        buton6.setBounds(10, 10, 104, 31);
        buton6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(cardPanel, "Kart1");
        	}
        });
        panel5.setLayout(null);
        buton6.setForeground(new Color(255, 255, 255));
        buton6.setBackground(new Color(0, 0, 0));
        buton6.setFont(new Font("Cambria", Font.BOLD, 12));
        panel5.add(buton6);
        
        JLabel lblNewLabel_1 = new JLabel("İşlemci");
        lblNewLabel_1.setBackground(new Color(0, 0, 0));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 25));
        lblNewLabel_1.setBounds(240, 67, 139, 31);
        panel5.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Anakart");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBackground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 25));
        lblNewLabel_2.setBounds(240, 272, 160, 38);
        panel5.add(lblNewLabel_2);
        
        JComboBox<String> işlemciler1 = new JComboBox<>(new String[]{});
        işlemciler1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        işlemciler1.setForeground(new Color(255, 255, 255));
        işlemciler1.setBackground(new Color(0, 0, 0));
        işlemciler1.setBounds(34, 108, 523, 30);
        panel5.add(işlemciler1);
        
        JComboBox<String> ekranKartları1 = new JComboBox<>(new String[]{});
        ekranKartları1.setForeground(Color.WHITE);
        ekranKartları1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ekranKartları1.setBackground(Color.BLACK);
        ekranKartları1.setBounds(34, 563, 523, 30);
        panel5.add(ekranKartları1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Ekran Kartı");
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setFont(new Font("Cambria", Font.BOLD, 25));
        lblNewLabel_2_1.setBackground(Color.BLACK);
        lblNewLabel_2_1.setBounds(229, 497, 160, 38);
        panel5.add(lblNewLabel_2_1);
        
        JComboBox<String> anakartlar1 = new JComboBox<>(new String[]{});
        anakartlar1.setForeground(Color.WHITE);
        anakartlar1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        anakartlar1.setBackground(Color.BLACK);
        anakartlar1.setBounds(34, 331, 523, 30);
        panel5.add(anakartlar1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Ram");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Cambria", Font.BOLD, 25));
        lblNewLabel_1_1.setBackground(Color.BLACK);
        lblNewLabel_1_1.setBounds(818, 65, 139, 31);
        panel5.add(lblNewLabel_1_1);
        
        JComboBox<String> ramler1 = new JComboBox<>(new String[]{});
        ramler1.setSelectedIndex(-1);
        ramler1.setForeground(Color.WHITE);
        ramler1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ramler1.setBackground(Color.BLACK);
        ramler1.setBounds(578, 108, 523, 30);
        panel5.add(ramler1);
        
        //SSD VE PSU LABEL VE COMBOBOX'LARI  
        JLabel lblNewLabel_1_1_1 = new JLabel("SSD\r\n");
        lblNewLabel_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1.setFont(new Font("Cambria", Font.BOLD, 25));
        lblNewLabel_1_1_1.setBackground(Color.BLACK);
        lblNewLabel_1_1_1.setBounds(818, 290, 139, 31);
        panel5.add(lblNewLabel_1_1_1);
        
        JComboBox<String> ssdler1 = new JComboBox<>(new String[]{});
        ssdler1.setForeground(Color.WHITE);
        ssdler1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ssdler1.setBackground(Color.BLACK);
        ssdler1.setBounds(578, 331, 523, 30);
        panel5.add(ssdler1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("PSU\r\n");
        lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1_1.setFont(new Font("Cambria", Font.BOLD, 25));
        lblNewLabel_1_1_1_1.setBackground(Color.BLACK);
        lblNewLabel_1_1_1_1.setBounds(818, 522, 139, 31);
        panel5.add(lblNewLabel_1_1_1_1);
        
        JComboBox<String> psular1 = new JComboBox<String>();
        psular1.setForeground(Color.WHITE);
        psular1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        psular1.setBackground(Color.BLACK);
        psular1.setBounds(578, 563, 523, 30);
        panel5.add(psular1);
        
        JButton buton4 = new JButton("Kendin Topla");
        buton4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//İŞLEMCİ COMBOBOX GÜNCELLEMESİ
        		String islemcilerr = pc.islemci_ll();

                String[] satirlar = islemcilerr.split("\n");
                for (String satır : satirlar) {
                    if (!satır.isEmpty()) { 
                        işlemciler1.addItem(satır);
                    }
                }
                işlemciler1.setSelectedIndex(-1);
        		
        		//EKRAN KARTI COMBOBOX GÜNCELLEMESİ
        		String ekranKartı = pc.ekranKartı_ll();

                String[] ekranKartıı = ekranKartı.split("\n");
                for (String satır : ekranKartıı) {
                    if (!satır.isEmpty()) { 
                        ekranKartları1.addItem(satır);
                    }
                }
                ekranKartları1.setSelectedIndex(-1);
        		
        		//ANAKART COMBOBOX GÜNCELLEMESİ
        		String anakartlar = pc.anakart_ll();

                String[] anakartt = anakartlar.split("\n");
                for (String satır : anakartt) {
                    if (!satır.isEmpty()) { 
                        anakartlar1.addItem(satır);
                    }
                }
                anakartlar1.setSelectedIndex(-1);
        		
        		//RAM COMBOBOX GÜNCELLEMESİ
        		String ramler = pc.ram_ll();

                String[] ramm = ramler.split("\n");
                for (String satır : ramm) {
                    if (!satır.isEmpty()) { 
                        ramler1.addItem(satır);
                    }
                }
                ramler1.setSelectedIndex(-1);
        		
        		//SSD COMBOBOX GÜNCELLEMESİ
        		String ssdler = pc.ssd_ll();

                String[] ssdd = ssdler.split("\n");
                for (String satır : ssdd) {
                    if (!satır.isEmpty()) { 
                        ssdler1.addItem(satır);
                    }
                }
                ssdler1.setSelectedIndex(-1);
        		
        		//PSU COMBOBOX GÜNCELLEMESİ
        		String psular = pc.psu_ll();

                String[] psuu = psular.split("\n");
                for (String satır : psuu) {
                    if (!satır.isEmpty()) { 
                        psular1.addItem(satır);
                    }
                }
                psular1.setSelectedIndex(-1);
        		cardLayout.show(cardPanel, "Kart 5");        		
        	}
        });
        buton4.setBackground(new Color(0, 0, 0));
        buton4.setForeground(new Color(255, 255, 255));
        buton4.setFont(new Font("Cambria", Font.BOLD, 12));
        buton4.setBounds(305, 62, 154, 56);
        panel1.add(buton4);
        cardPanel.add(panel2, "Kart2");
        
        JButton btnOnayla = new JButton("Sepete Ekle");
        btnOnayla.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(işlemciler1.getSelectedItem() == null || anakartlar1.getSelectedItem() == null || ekranKartları1.getSelectedItem() == null || ramler1.getSelectedItem() == null || ssdler1.getSelectedItem() == null || psular1.getSelectedItem() == null) {
        			JOptionPane.showMessageDialog(btnOnayla, "Lütfen parça seçiminizi eksiksiz yapınız!");
        		}else {
        			String islemci = (String) işlemciler1.getSelectedItem();
            		String ekranKartı = (String) ekranKartları1.getSelectedItem();
            		String anakart = (String) anakartlar1.getSelectedItem();
            		String ram = (String) ramler1.getSelectedItem();
            		String ssd = (String) ssdler1.getSelectedItem();
            		String psu = (String) psular1.getSelectedItem();
            		
            		String selectedProcessor = işlemciler1.getSelectedItem().toString();
            		String selectedMotherboard = anakartlar1.getSelectedItem().toString();
            		String selectedRAM = ramler1.getSelectedItem().toString();
            		
            		//Processor'u ayır
            		String[] processorParts = selectedProcessor.split(",");
            		String processorName = processorParts[0]; 

            		// Motherboard'u ayır
            		String[] motherboardParts = selectedMotherboard.split(",");
            		String motherboardName = motherboardParts[0]; 

            		// RAM'i ayır
            		String[] ramParts = selectedRAM.split(",");
            		String ramName = ramParts[0];

            		pc.checkSelectedCompatibility(processorName, motherboardName, ramName);
            		if(pc.değer == 1) {
            			JOptionPane.showMessageDialog(btnOnayla, "Bileşenler birbiriyle uyumlu, bilgisayar sepete eklendi!");
            			String sırayaEklenecekBilgisayar = "İşlemci: " + islemci + " Anakart: " + anakart + " Ekran Kartı: " + ekranKartı + " Ram: " + ram + " Ssd: " + ssd + " Psu: " + psu + "\n"; 
            			queue.enqueue(sırayaEklenecekBilgisayar);
            		}else {
            			JOptionPane.showMessageDialog(btnOnayla, "Bileşenler birbiriyle uyumsuz, sepete eklenmedi!(Soket tipi ve ddr türüne dikkat ediniz)");
            		}
        		}
        	}
        });
        btnOnayla.setForeground(Color.WHITE);
        btnOnayla.setFont(new Font("Cambria", Font.BOLD, 17));
        btnOnayla.setBackground(Color.BLACK);
        btnOnayla.setBounds(146, 673, 207, 31);
        panel5.add(btnOnayla);
        
        JButton btnOnayla_1_2 = new JButton("Sepeti Görüntüle");
        btnOnayla_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(queue.uzunluk == 0) {
        			JOptionPane.showMessageDialog(btnOnayla_1_2, "Sepet boş!");
        		}else {
        			JOptionPane.showMessageDialog(btnOnayla_1_2, queue.goster());
        		}
        	}
        });
        btnOnayla_1_2.setForeground(Color.WHITE);
        btnOnayla_1_2.setFont(new Font("Cambria", Font.BOLD, 17));
        btnOnayla_1_2.setBackground(Color.BLACK);
        btnOnayla_1_2.setBounds(518, 673, 200, 31);
        panel5.add(btnOnayla_1_2);
        
        JButton btnOnayla_1_3 = new JButton("Sipariş Ver");
        btnOnayla_1_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(queue.uzunluk == 0) {
        			JOptionPane.showMessageDialog(btnOnayla_1_3, "Önce bilgisayar oluşturun, sepet boş!");
        		}else {
        			JOptionPane.showMessageDialog(btnOnayla_1_3, "Sayın " + nameField.getText() + " " + surnameField.getText() + ", sepete eklediğiniz ilk bilgisayar için siparişiniz alınmıştır!");
        			queue.dequeue();
        		}
        	}
        });
        btnOnayla_1_3.setForeground(Color.WHITE);
        btnOnayla_1_3.setFont(new Font("Cambria", Font.BOLD, 17));
        btnOnayla_1_3.setBackground(Color.BLACK);
        btnOnayla_1_3.setBounds(728, 673, 203, 31);
        panel5.add(btnOnayla_1_3);
        
        pc.saveComponentsToFile("components.txt");
        }
    
     //Özel arka plan resmi çizen panel sınıfı.
    private static class BackgroundPanel extends JPanel {
        private ImageIcon backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon("duvarkağıdı.jpg");
            } catch (Exception e) {
                System.err.println("Arka plan resmi yüklenemedi: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
