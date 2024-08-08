        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    // รับไฟล์ที่เลือกมาเก็บในตัวแปร
                    File selectedFile = fileChooser.getSelectedFile();
                    
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"))) {
                        String line;
                        String[][] dataGrid = new String[10][20];
                        int row = 0;
                        
                        while ((line = reader.readLine()) != null && row < 10) {
                            String[] tokens = line.split(" "); // สมมติว่าใช้ช่องว่างแบ่งข้อมูล
                            for (int col = 0; col < tokens.length && col < 20; col++) {
                                dataGrid[row][col] = tokens[col];
                            }
                            row++;
                        }

                        // แสดงผลในคอนโซล (สามารถเปลี่ยนเป็นแสดงใน GUI)
                        for (String[] dataRow : dataGrid) {
                            for (String element : dataRow) {
                                System.out.print(element + " ");
                            }
                            System.out.println();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });