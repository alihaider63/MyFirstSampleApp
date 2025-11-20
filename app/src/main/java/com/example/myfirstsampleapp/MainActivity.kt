package com.example.myfirstsampleapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstsampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MyInterface {

    companion object {
        const val DRAWABLE_EXTRA = "drawable"
        const val BITMAP_EXTRA = "bitmap"

        private const val REQUEST_CODE = 42
    }

    private var adapter: MyAdapter? = null

    private val butterflyObjects = arrayListOf(
        Butterfly("Argusvlinder", "(Lasiommata megera)", R.drawable.butterfly1),
        Butterfly("Bont Zandoogje", "(Pararge aegeria)", R.drawable.butterfly2),
        Butterfly("Bruin Zandoogje", "(Maniola jurtina)", R.drawable.butterfly3),
        Butterfly("Distelvlinder", "(Vanessa cardui)", R.drawable.butterfly4),
        Butterfly("Distelvlinder", "(Vanessa cardui)", R.drawable.butterfly5),
        Butterfly("Distelvlinder", "(Vanessa cardui)", R.drawable.butterfly6),
        Butterfly("Zwartsprietdikkopje", "(Thymelicus lineola)", R.drawable.butterfly7),
        Butterfly("Bretons spikkeldikkopje", "(Pyrgus armoricanus)", R.drawable.butterfly8),
        Butterfly("Aardbeivlinder", "(Pyrgus malvae)", R.drawable.butterfly9),
        Butterfly("Koninginnenpage", "(Papilio machaon)", R.drawable.butterfly10)
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MyAdapter(butterflyObjects, this)

        binding.listOfButterfly.adapter = adapter
        binding.listOfButterfly.layoutManager = LinearLayoutManager(this)

        binding.addNewItem.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (cameraIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(cameraIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable To open Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val takenImage = data?.extras?.get("data") as? Bitmap
            takenImage?.let {
                adapter?.addItem(Butterfly("Ali", "Haider", null, it, 2))
                Toast.makeText(this, "Successfully Added Item.", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onButterClicked(butterfly: Butterfly) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(DRAWABLE_EXTRA, butterfly.butterfly)
        intent.putExtra(BITMAP_EXTRA, butterfly.image)
        startActivity(intent)
    }

}