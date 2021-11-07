# mobilLab7

## Задание:
  Разработать простое мобильное приложение для сохранения данных из поля в тестовый файл, а также считывать информацию из него;
## Решение:
1)Создадим проект, добавим поле ввода, label и 2 кнопки
2)Добавим обработчики кнопок:
```Java
public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME = "outputFile.txt";
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this::saveData);
        Button loadBtn = findViewById(R.id.loadBtn);
        loadBtn.setOnClickListener(this::loadData);
    }
    private void saveData(View view){
        try(FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE)){
            EditText textBox = (EditText) findViewById(R.id.saveText);
            byte[] buf = textBox.getText().toString().getBytes();
            fos.write(buf,0,buf.length);
            fos.close();
            Toast.makeText(this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Файл не найден!", Toast.LENGTH_SHORT).show();
        } catch (IOException ex){
            ex.printStackTrace();
            Toast.makeText(this,"Ошибка сохранения файла!",Toast.LENGTH_SHORT).show();
        }
    }
    private void loadData(View view){
        try(FileInputStream fin = openFileInput(FILE_NAME)){
            byte[] buf = new byte[fin.available()];
            fin.read(buf);
            String text = new String(buf);
            TextView textView = findViewById(R.id.outputText);
            textView.setText(text);
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
            Toast.makeText(this, "Файл не найден!", Toast.LENGTH_SHORT).show();
        } catch (IOException ex){
            ex.printStackTrace();
            Toast.makeText(this,"Ошибка чтения файла!", Toast.LENGTH_SHORT).show();
        }

    }
}
```

3)Добавим разрешения:
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```
4)Проверим работу...
