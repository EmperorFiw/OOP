    public void addStatus() {
        String[] texts = {"Kuy1", "Hee1", "Kuy2", "Hee2", "Kuy3", "Hee3"}; // ข้อความทั้งหมด 6 ข้อความ
        String[] data = {"123", "123", "123", "123", "123", "1211331212313"};

        for (int i = 0; i < texts.length; i++) {
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.WEST;
            Label lb = new Label(texts[i] + ": " + data[i]); // ใส่ชื่อ label
            lb.setFont(new Font("Arial", Font.BOLD, 15)); // ปรับเป็นขนาด Font
            status.add(lb, gbc); // เพิ่ม label เข้า panelR1
        }
    }