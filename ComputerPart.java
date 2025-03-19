package donemProjesi;

abstract class Component {
    String name;
    double price;

    public Component(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Her sınıfta override edilecek toTextFormat metodu
    public abstract String toTextFormat();
}

class Processor extends Component {
    String socketType;

    public Processor(String name, double price, String socketType) {
        super(name, price);
        this.socketType = socketType;
    }

    @Override
    public String toTextFormat() {
        return "Processor," + name + "," + price + "," + socketType;
    }
}

class Motherboard extends Component {
    String socketType;
    String ddrType;

    public Motherboard(String name, double price, String socketType, String ddrType) {
        super(name, price);
        this.socketType = socketType;
        this.ddrType = ddrType;
    }

    @Override
    public String toTextFormat() {
        return "Motherboard," + name + "," + price + "," + socketType + "," + ddrType;
    }
}

class RAM extends Component {
    String ddrType;

    public RAM(String name, double price, String ddrType) {
        super(name, price);
        this.ddrType = ddrType;
    }

    @Override
    public String toTextFormat() {
        return "RAM," + name + "," + price + "," + ddrType;
    }
}

class GPU extends Component {
    public GPU(String name, double price) {
        super(name, price);
    }

    @Override
    public String toTextFormat() {
        return "GPU," + name + "," + price;
    }
}

class SSD extends Component {
    int capacity; // Depolama kapasitesi (GB)

    public SSD(String name, double price, int capacity) {
        super(name, price);
        this.capacity = capacity;
    }

    @Override
    public String toTextFormat() {
        return "SSD," + name + "," + price + "," + capacity;
    }
}

class PSU extends Component {
    int wattage; // Güç kapasitesi (Watt)

    public PSU(String name, double price, int wattage) {
        super(name, price);
        this.wattage = wattage;
    }

    @Override
    public String toTextFormat() {
        return "PSU," + name + "," + price + "," + wattage;
    }
}
