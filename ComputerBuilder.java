package donemProjesi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ComputerBuilder {
    public LinkedList<Component> componentList = new LinkedList<>();
    public Queue<String> queue = new LinkedList<>();

    public void main(String[] args) {
        loadComponentsFromFile("components.txt");

        System.out.println("Tüm bileşenler:");
        displayComponents();

        removeComponent("ExampleComponent");
        System.out.println("\nBir bileşen çıkarıldıktan sonra:");
        displayComponents();
    }

    // Text dosyasından linked liste verileri aktarma
    public void loadComponentsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Fazla boşlukları temizle
                line = line.trim();
                
                // Satırı virgüllere göre ayır
                String[] parts = line.split(",");
                
                // Temel bilgileri oku ve temizle
                String type = parts[0].trim();
                String name = parts[1].trim();
                double price;

                try {
                    price = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Hatalı fiyat değeri! Satır atlanıyor: " + line);
                    continue;
                }
                try {
                    switch (type) {
                        case "Processor":
                            if (parts.length < 4) {
                                System.err.println("Eksik işlemci bilgisi! Satır atlanıyor: " + line);
                                continue;
                            }
                            componentList.add(new Processor(name, price, parts[3].trim()));
                            break;
                        case "Motherboard":
                            if (parts.length < 5) {
                                System.err.println("Eksik anakart bilgisi! Satır atlanıyor: " + line);
                                continue;
                            }
                            componentList.add(new Motherboard(name, price, parts[3].trim(), parts[4].trim()));
                            break;
                        case "RAM":
                            if (parts.length < 4) {
                                System.err.println("Eksik RAM bilgisi! Satır atlanıyor: " + line);
                                continue;
                            }
                            componentList.add(new RAM(name, price, parts[3].trim()));
                            break;
                        case "GPU":
                            componentList.add(new GPU(name, price));
                            break;
                        case "SSD":
                            if (parts.length < 4) {
                                System.err.println("Eksik SSD bilgisi! Satır atlanıyor: " + line);
                                continue;
                            }
                            int capacity = Integer.parseInt(parts[3].trim());
                            componentList.add(new SSD(name, price, capacity));
                            break;
                        case "PSU":
                            if (parts.length < 4) {
                                System.err.println("Eksik PSU bilgisi! Satır atlanıyor: " + line);
                                continue;
                            }
                            int wattage = Integer.parseInt(parts[3].trim());
                            componentList.add(new PSU(name, price, wattage));
                            break;
                        default:
                            System.err.println("Bilinmeyen bileşen türü! Satır atlanıyor: " + line);
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Hatalı sayı değeri! Satır atlanıyor: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Dosya okunamadı: " + e.getMessage());
        }
    }
    
 // Linked listteki bileşenleri text dosyasına yazma
    public void saveComponentsToFile(String fileName) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (Component component : componentList) {

                // Bileşeni text formatına çevir ve dosyaya yaz
                bw.write(component.toTextFormat());

                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Dosya yazılamadı: " + e.getMessage());
        }
    }



    // Bileşenleri görüntüle
    public String displayComponents() {
        StringBuilder sb = new StringBuilder();
        for (Component component : componentList) {
            if (component instanceof Processor) {
                Processor p = (Processor) component;
                sb.append("Processor: ").append(p.name).append(", Price: ").append(p.price).append(", Socket: ").append(p.socketType).append("\n");
            } else if (component instanceof Motherboard) {
                Motherboard m = (Motherboard) component;
                sb.append("Motherboard: ").append(m.name).append(", Price: ").append(m.price).append(", Socket: ").append(m.socketType).append(", DDR: ").append(m.ddrType).append("\n");
            } else if (component instanceof RAM) {
                RAM r = (RAM) component;
                sb.append("RAM: ").append(r.name).append(", Price: ").append(r.price).append(", DDR: ").append(r.ddrType).append("\n");
            } else if (component instanceof GPU) {
                GPU g = (GPU) component;
                sb.append("GPU: ").append(g.name).append(", Price: ").append(g.price).append("\n");
            }else if (component instanceof SSD) {
                SSD ssd = (SSD) component;
                sb.append("SSD: ").append(ssd.name).append(", Price: ").append(ssd.price).append(ssd.capacity).append("\n");
            }else if (component instanceof PSU) {
                PSU psu = (PSU) component;
                sb.append("PSU: ").append(psu.name).append(", Price: ").append(psu.price).append(psu.wattage).append("\n");
            }
        }
        return sb.toString();
    }
    
    String bileşenler = displayComponents();
    
    public String getbileşenler() {
    	return bileşenler;
    }
    
    public void setBilesenler(String bilesenler) {
        this.bileşenler = bilesenler;
    }

    
    public String whole_ll() {
    	StringBuilder all_components = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof Component) {
                Component p = (Component) component;
                all_components.append(p.name).append(", Fiyat: ").append(p.price).append("\n");
            }
    	}
    	return all_components.toString();
    }
    
    public String islemci_ll() {
    	StringBuilder islemciler = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof Processor) {
                Processor p = (Processor) component;
                islemciler.append(p.name).append(", Fiyat: ").append(p.price).append(" ").append(p.socketType).append("\n");
            }
    	}
    	return islemciler.toString();
    }
    
    public String anakart_ll() {
    	StringBuilder anakartlar = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof Motherboard) {
                Motherboard p = (Motherboard) component;
                anakartlar.append(p.name).append(", Fiyat: ").append(p.price).append(" Soket: ").append(p.socketType).append(" ").append(p.ddrType).append("\n");
            }
    	}
    	return anakartlar.toString();
    }
    
    public String ekranKartı_ll() {
    	StringBuilder ekranKartları = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof GPU) {
                GPU p = (GPU) component;
                ekranKartları.append(p.name).append(", Fiyat: ").append(p.price).append("\n");
            }
    	}
    	return ekranKartları.toString();
    }
    
    public String ram_ll() {
    	StringBuilder ramler = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof RAM) {
                RAM p = (RAM) component;
                ramler.append(p.name).append(", Fiyat: ").append(p.price).append(" ").append(p.ddrType).append("\n");
            }
    	}
    	return ramler.toString();
    }
    
    public String ssd_ll() {
    	StringBuilder ssdler = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof SSD) {
                SSD p = (SSD) component;
                ssdler.append(p.name).append(", Fiyat: ").append(p.price).append("\n");
            }
    	}
    	return ssdler.toString();
    }
    
    public String psu_ll() {
    	StringBuilder psular = new StringBuilder();
    	for (Component component : componentList) {
            if (component instanceof PSU) {
                PSU p = (PSU) component;
                psular.append(p.name).append(", Fiyat: ").append(p.price).append("\n");
            }
    	}
    	return psular.toString();
    }
    


    // Bileşen çıkarma
    public void removeComponent(String componentName) {
        componentList.removeIf(component -> component.name.equals(componentName));
    }
    
    int sayac = 0;
    
    public void addComponent(String type, String name, double price, String... attributes) {
        Component newComponent = null;

        switch (type) {
            case "Processor":
                if (attributes.length >= 1) {
                    newComponent = new Processor(name, price, attributes[0]);
                }
                break;

            case "Motherboard":
                if (attributes.length >= 2) {
                    newComponent = new Motherboard(name, price, attributes[0], attributes[1]);
                }
                break;

            case "RAM":
                if (attributes.length >= 1) {
                    newComponent = new RAM(name, price, attributes[0]);
                }
                break;

            case "GPU":
                newComponent = new GPU(name, price);
                break;

            case "SSD":
                if (attributes.length >= 1) {
                    try {
                        int capacity = Integer.parseInt(attributes[0]);
                        newComponent = new SSD(name, price, capacity);
                    } catch (NumberFormatException e) {
                        System.out.println("SSD kapasitesi hatalı bir sayı formatında.");
                        return;
                    }
                }
                break;

            case "PSU":
                if (attributes.length >= 1) {
                    try {
                        int wattage = Integer.parseInt(attributes[0]);
                        newComponent = new PSU(name, price, wattage);
                    } catch (NumberFormatException e) {
                        System.out.println("PSU watt değeri hatalı bir sayı formatında.");
                        return;
                    }
                }
                break;

            default:
                sayac = 3;
                return;
        }

        if (newComponent != null) {
            componentList.add(newComponent);
            sayac = 1;
        } else {
        	sayac = 2;
        }
    }
    
    public int değer;

    public String checkSelectedCompatibility(String processorName, String motherboardName, String ramName) {
        Processor selectedProcessor = null;
        Motherboard selectedMotherboard = null;
        RAM selectedRAM = null;

        // İstenen işlemci, anakart ve ram'i seç
        for (Component component : componentList) {
            if (component instanceof Processor && component.name.equals(processorName)) {
                selectedProcessor = (Processor) component;
            } else if (component instanceof Motherboard && component.name.equals(motherboardName)) {
                selectedMotherboard = (Motherboard) component;
            } else if (component instanceof RAM && component.name.equals(ramName)) {
                selectedRAM = (RAM) component;
            }
        }
        if (selectedProcessor == null) {
            değer = 0; // İşlemci bulunamadıysa
            return "Hata: İşlemci '" + processorName + "' bulunamadı!";
        }
        if (selectedMotherboard == null) {
            değer = 0; // Anakart bulunamadıysa
            return "Hata: Anakart '" + motherboardName + "' bulunamadı!";
        }
        if (selectedRAM == null) {
            değer = 0; // RAM bulunamadıysa
            return "Hata: RAM '" + ramName + "' bulunamadı!";
        }

        // İşlemci ve anakart uyumluluğu
        if (!selectedProcessor.socketType.equals(selectedMotherboard.socketType)) {
            değer = 0; 
            return "İşlemci ile anakart uyumsuz: Soket tipleri uyuşmuyor!";
        }

        // RAM ve anakart uyumluluğu
        if (!selectedRAM.ddrType.equals(selectedMotherboard.ddrType)) {
            değer = 0; 
            return "Anakart ile RAM uyumsuz: DDR tipleri uyuşmuyor!";
        }

        // Eğer her şey uyumlu ise
        değer = 1; 
        return "İşlemci, Anakart ve RAM uyumlu.";
    }

    
}