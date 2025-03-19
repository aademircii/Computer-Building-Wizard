package donemProjesi;

import java.util.ArrayList;
import java.util.List;

class Node {
    String data;
    Node sonraki;
    
    Node(String data) {
        this.data = data;
        this.sonraki = null;
    }
}

class BagliListeKuyruk {
    Node on, arka;
    public int uzunluk;
    
    BagliListeKuyruk() {
        this.on = this.arka = null;
        this.uzunluk = 0; 
    }
    
    // Arkadan ekleme 
    void enqueue(String item) {
        Node yeni = new Node(item);
        
        // Eğer kuyruk boşsa
        if (this.arka == null) {
            this.on = this.arka = yeni;
        } else {
            this.arka.sonraki = yeni;
            this.arka = yeni;
        }
        uzunluk++;
    }
    
    // Baştan silme
    void dequeue() {
        // Eğer kuyruk boşsa
        if (this.on == null) {
            return;
        }
        
        Node yeni = this.on.sonraki;
        this.on.sonraki = null;
        this.on = yeni;
        
        if (this.on == null) {
            this.arka = null;
        }
        uzunluk--;
    }
    
    // Kuyruğu göster
    public List<String> goster() {
        List<String> elemanlar = new ArrayList<>();
        Node yeni = this.on;

        while (yeni != null) {
            elemanlar.add(yeni.data);
            yeni = yeni.sonraki;
        }

        return elemanlar; 
    }
}
