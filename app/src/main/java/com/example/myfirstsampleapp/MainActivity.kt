package com.example.myfirstsampleapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class MainActivity : AppCompatActivity(), MyInterface {

    companion object{
        const val DRAWABLE_EXTRA = "drawable"
        const val BITMAP_EXTRA = "bitmap"
        //const val IMAGE_PATH_EXTRA = "image_path"
    }

    private lateinit var photoFile: File
    private val FILE_NAME = "photo.jpg"
    private val REQUEST_CODE = 42
    //private lateinit var bitmap: Bitmap
    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewItemButton: Button

    private val butterflyObjects = mutableListOf(
        Butterfly("Argusvlinder","(Lasiommata megera)", R.drawable.butterfly1),
        Butterfly("Bont Zandoogje","(Pararge aegeria)", R.drawable.butterfly2),
        Butterfly("Bruin Zandoogje","(Maniola jurtina)", R.drawable.butterfly3),
        Butterfly("Distelvlinder","(Vanessa cardui)", R.drawable.butterfly4),
        Butterfly("Distelvlinder","(Vanessa cardui)", R.drawable.butterfly5),
        //  Butterflies(R.drawable.butterfly5,"Distelvlinder","(Vanessa cardui)",),
        Butterfly("Distelvlinder","(Vanessa cardui)", R.drawable.butterfly6),
        Butterfly("Zwartsprietdikkopje","(Thymelicus lineola)", R.drawable.butterfly7),
        Butterfly("Bretons spikkeldikkopje","(Pyrgus armoricanus)", R.drawable.butterfly8),
        Butterfly("Aardbeivlinder","(Pyrgus malvae)", R.drawable.butterfly9),
        Butterfly("Koninginnenpage","(Papilio machaon)", R.drawable.butterfly10)
//        Butterflies(R.drawable.butterfly10,"Koninginnenpage","(Papilio machaon)",),
//        Butterflies(R.drawable.butterfly10,"Koninginnenpage","(Papilio machaon)",)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //adapter = MyAdapter(butterflyObjects, ::onButterClicked)
        adapter = MyAdapter(butterflyObjects,this)
        recyclerView = findViewById(R.id.listOfButterfly)
        addNewItemButton = findViewById(R.id.addNewItem)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addNewItemButton.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


            /*photoFile = getPhotoFile(FILE_NAME)
            val fileProvider = FileProvider.getUriForFile(this,
                "com.example.myfirstsampleapp.fileprovider",photoFile)
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)*/

            if (cameraIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(cameraIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable To open Camera", Toast.LENGTH_SHORT).show()
            }
            //takePic()
            //addInfo()
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,"jpg",storageDirectory)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            //val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            takenImage?.let {
                adapter.addItem(Butterfly("Ali","Haider",null,it,2))
                Toast.makeText(this, "Successfully Added Item.", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onButterClicked(position: Int, butterfly: MutableList<Butterfly>) {
        val intent = Intent(this,SecondActivity::class.java)
        if (butterfly[position].butterfly != null) {
            intent.putExtra(DRAWABLE_EXTRA, butterfly[position].butterfly)
            startActivity(intent)
        } else {
            intent.putExtra(BITMAP_EXTRA, butterfly[position].image)
            startActivity(intent)
        }

    }

    /*  private fun onButterClicked(position: Int) {
      val intent = Intent(this,SecondActivity::class.java)
      intent.putExtra("image",butterflyObjects[position].butterfly)
      startActivity(intent)
  }
  */


    /*   private fun addInfo() {
           val inflater = LayoutInflater.from(this)
           val view = inflater.inflate(R.layout.dialogview,null)
           val imageClick = view.findViewById<Button>(R.id.clickImage)
           val butterfly = view.findViewById<EditText>(R.id.addButterfly)
           val family = view.findViewById<EditText>(R.id.family)
           val imageView = view.findViewById<ImageView>(R.id.ggggg)

           imageClick.setOnClickListener {
               takePic()
           }
           val addDialog = AlertDialog.Builder(this)
           addDialog.setView(view)
           addDialog.setPositiveButton("Ok") {
                   dialog,_->

               val butterflyNames = butterfly.text.toString()
               val familyCategory = family.text.toString()
               adapter.addItem(Butterfly(butterflyNames,familyCategory,null,bitmap,110))
               Toast.makeText(this, "Successfully Item Added.", Toast.LENGTH_SHORT).show()
               dialog.dismiss()
           }
           addDialog.setNegativeButton("Cancel") {
                   dialog,_->
               dialog.dismiss()
               Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
           }
           addDialog.create()
           addDialog.show()
       }*/
}