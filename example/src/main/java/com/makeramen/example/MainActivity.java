package com.makeramen.example;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.makeramen.example.sqlite.SQLiteHelper;


import java.io.File;
import java.util.List;

public class MainActivity extends Activity //implements ActionBar.OnNavigationListener {
{
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//inserta el fragmento en la actividad (es como insertar un panel en el java clasico)
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .replace(android.R.id.content, new MainFragment())
          .commit();
    }
      SQLiteHelper db = new SQLiteHelper(this);//creo el objeto que me ayudara a manejar la conexion con base de datos

      int resId=0;
      Object[] lugar;

      File database=getApplicationContext().getDatabasePath("LugaresDB");// creo un objeto tipo file para verificar si existe la base de datos
        //si la base de datos no existe entonces se crea
      if (!database.exists()) {
          // Database does not exist so copy it from assets here
          /*Lista de Tablas disponibles:
          * bares
          * cita
          * comida
          * deporte
          * discotecas
          * karaoke
          * sano
          *
          *
          *
          *
          * Los parametros que van en la tabla son los siguientes: nombre, direccion, telefono, informacion del lugar como precios, res.Id es el numero de la foto,
          * android identifica los recursos de la aplicacion como imagenes a travez de enteros, el null es para una segunda imagen si esta disponible
          * */
          //esta seccion es para agregar discotecas a la tabla discotecas de la base de datos
          Log.i("Database", "No se Encuentra");
          /*resId = R.drawable.blue;// R.drawable.nombre de la foto para cada lugar... tratar de poner las fotos con el nombre del lugar asi es mas facil asociar
          lugar = new Object[]{"BLUE","Jose Calama E-544 y Juan Leon, Quito - Ecuador","0983844623","En este lugar se vende tanto",resId,null};
          db.addLugar("discotecas",lugar);*/

          resId = R.drawable.blue;
          lugar = new Object[]{"Blue", "José Calama y Juan León Mera, Quito, Ecuador", "0983844623", "Las mejores bebidas, cócteles, micheladas, jarras de vodka, ron, laguna azul, kaipiriña, diferentes tipos de cerveza, todas nuestras bebidas son garantizadas.", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.brau;
          lugar = new Object[]{"Brau Platz", "Foch y Reina Victoria, Quito, Ecuador", "(02) 604-0134", "Cócteles, micheladas, jarras de vodka, ron, laguna azul, entre otros...", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.buenosaires;
          lugar = new Object[]{"Buenos Aires News", "Calama y Juan León Mera, Quito, Ecuador", "0998218576", "La mejor discoteca de la zona rosa, con exclusividad en licores las mejores marcas, coctelería de primera, micheladas.", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.bungalow;
          lugar = new Object[]{"Bungalow 6", "Diego de Almagro y Calama, Quito, Ecuador", "-", "La mejor farra con uno de los mejores D'j de la capital, varios ambientes, único y exclusivo, picaditas, coctelería con licores certificados", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.caribe;
          lugar = new Object[]{"Caribe Son", "República , Quito, Ecuador", "0999210878", "Excelente atención; instalaciones totalmente seguras, con circuito cerrado de televisión y amplios parqueaderos. Música en vivo, coctelería, seguridad privada, atención personalizada", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.cirque;
          lugar = new Object[]{"Cirque", "Joaquín Pinto y Reina Victoria, Quito, Ecuador", "(02) 290-2092", "Las mejores bebidas, con una gran variedad en licores, coctelería, cervezas, además de la mejor gastronomía de la zona con precios únicos en un ambiente diferente", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.clown;
          lugar = new Object[]{"Clown Bar", "Foch y Reina Victoria, Quito, Ecuador", "0995918526", "Coctelería variada, picaditas, sandwiches, pizza, alitas bbq, picadas de quesos jamones, nachos con queso y guacamole, las micheladas y mojitos gigantes, zona wi-fi gratis", resId, null};
          db.addLugar("Discotecas", lugar);
		  
		  resId = R.drawable.tuneldeltiempo;
          lugar = new Object[]{"El Túnel del Tiempo", "Av. General Enríquez y La Concordia, Quito, Ecuador", "0998858312", "La mejor fiesta retro, con música en vivo, proyección de los mejores vídeos clásicos en pantalla gigante, una gran variedad en bebidas y cócteles. Seguridad privada y parqueaderos", resId, null};
          db.addLugar("Discotecas", lugar);
		  
          //esta seccion es para agregar bares a la tabla bares de la base de datos
          //las direcciones no incluir numeros, solo calles principales con intersecciones si estan disponibles, asi google maps puede encontrar el lugar

          //resId = R.drawable.brau;
          //lugar = new Object[]{"BRAU", "Foch E7-30 y Reina Victoria, Quito - Ecuador", "(02) 604-0134", "En este lugar se vende tal cosa", resId, null};
          //db.addLugar("bares", lugar);
			resId = R.drawable.achiotebar;
			lugar = new Object[]{"Achiote", "Juan Rodríguez y Reina Victoria, Quito, Ecuador", "(02) 250-1743", "No hay informacion", resId, null};
			db.addLugar("Bares", lugar);

			resId = R.drawable.azuca;
			lugar = new Object[]{"Azuca Latin Bistro", "Foch y Reina Victoria, Quito, Ecuador", "(02) 290-7164", "Ropa Vieja , la original cubana, sabroso lomo de res deshilachado al estilo cubano con arroz moro, maduros fritos y vegetales frescos.", resId, null};
			db.addLugar("Bares", lugar);

			resId = R.drawable.bbqco;
			lugar = new Object[]{"BBQ & CO.", "Foch y Reina Victoria, Quito, Ecuador", "(02) 604-0134", "Gran Variedad de opciones! Hamburguesas, Mojitos, Alitas y los mejores cortes de carne, Tú decides! Mira nuestro menú y promociones", resId, null};
			db.addLugar("Bares", lugar);

			resId = R.drawable.crepesparis;
			lugar = new Object[]{"Crêpes de Paris", "Calama y Diego de Almagro, Quito, Ecuador", "0994000480", "Los mejores crêpes de sal y dulce al estilo de Paris", resId, null};
			db.addLugar("Bares", lugar);

			resId = R.drawable.dirtysanchez;
			lugar = new Object[]{"Dirty Sánchez", "Joaquin Pinto y Reina Victoria, Quito, Ecuador", "(02) 255-1810", "No solo complacemos los gustos de los amantes del café, también ofrecemos una gran variedad de cocteles y picadas", resId, null};
			db.addLugar("Bares", lugar);
			
			resId = R.drawable.latunda;
			lugar = new Object[]{"La Tunda", "Reina Victoria, Quito, Ecuador", "0997925318", "Restaurante con comida Fussion Esmeraldeña e internacional, picaditas, comida rápida alitas con diferentes tipos de salsas, bebidas, coctelería, jugos Fussion, micheladas en bota mexicana, seguridad privada, varios ambientes.", resId, null};
			db.addLugar("Bares", lugar);
			
			resId = R.drawable.mexicali;
			lugar = new Object[]{"Mexicali Gspot", "José Calama y Diego de Almagro, Quito, Ecuador", "0992947380", "Gran variedad de comida mexicana, hamburguesas, sanduches, picaditas, Hot dogs, cervezas de varias marcas, coctelería a los mejores precios", resId, null};
			db.addLugar("Bares", lugar);
			
			resId = R.drawable.mongos;
			lugar = new Object[]{"Mongo's", "Calama y Juán León Mera, Quito, Ecuador", "0992748696", "Picaditas, entradas y platos fuertes con un toque asiático caracterísitico de Mongolia.", resId, null};
			db.addLugar("Bares", lugar);
			
          //cita
          //resId = R.drawable.brau;
          //lugar = new Object[]{"BRAU", "Foch E7-30 y Reina Victoria, Quito - Ecuador", "(02) 604-0134", "En este lugar se vende tal cosa", resId, null};
          //db.addLugar("cita", lugar);
			resId = R.drawable.achiote;
			lugar = new Object[]{"Achiote Ecuador Cuisine", "Juan Rodriguez y Reina Victoria, Quito, Ecuador", "(593) 022501743", "Cocina: Ecuatoriana", resId, null};
			db.addLugar("Cita", lugar);
			
			resId = R.drawable.carmine;
			lugar = new Object[]{"Carmine Gastronomía & Arte", "Catalina Aldaz y Portugal, Quito, Ecuador", "(593) 333282996", "Cocina: Italiana, Mediterránea, Internacional", resId, null};
			db.addLugar("Cita", lugar);
			
			resId = R.drawable.jerome;
			lugar = new Object[]{"Chez Jerome", "Wymper y Coruna, Quito, Ecuador", "(02) 600-0669", "Cocina: Francesa", resId, null};
			db.addLugar("Cita", lugar);

			resId = R.drawable.pavarotti;
			lugar = new Object[]{"Pavarotti Ristorante", "Av 12 de Octubre y Luis Cordero, Quito, Ecuador", "(593) 22566668", "Cocina: Italiana", resId, null};
			db.addLugar("Cita", lugar);

			resId = R.drawable.segundomuelle;
			lugar = new Object[]{"Segundo Muelle", "Ave. Isabel la Catolica y Gangotena, Quito, Ecuador", "(593) 22226548", "Cocina: Peruana", resId, null};
			db.addLugar("Cita", lugar);
			
			resId = R.drawable.theatrum;
			lugar = new Object[]{"Theatrum", "Calle Manabi, Quito, Ecuador", "(593) 22571011", "Cocina: Mediterránea, Ecuatoriana, Internacional", resId, null};
			db.addLugar("Cita", lugar);
			
			resId = R.drawable.zao;
			lugar = new Object[]{"ZAO", "Eloy Alfaro y San Salvador, Quito, Ecuador", "-", "Cocina: Asiática", resId, null};
			db.addLugar("Cita", lugar);
			
			resId = R.drawable.zazu;
			lugar = new Object[]{"Zazu", "Mariano Aguilera y La Pradera, Quito, Ecuador", "(593) 22543559", "Cocina: Ecuatoriana, Internacional", resId, null};
			db.addLugar("Cita", lugar);

          //comida
          //resId = R.drawable.brau;
          //lugar = new Object[]{"BRAU", "Foch E7-30 y Reina Victoria, Quito - Ecuador", "(02) 604-0134", "En este lugar se vende tal cosa", resId, null};
          //db.addLugar("comida", lugar);
			resId = R.drawable.caravana;
			lugar = new Object[]{"Caravana Fast Food", "Chile Y Montufar, Quito, Ecuador", "(593) 2285262", "Pollo asado, burguer, papas fritas, postres, entre otros", resId, null};
			db.addLugar("Comida", lugar);
			
			resId = R.drawable.corral;
			lugar = new Object[]{"Hamburguesas El Corral", "Av.Amazonas, Quito, Ecuador", "-", "Hamburguesas a la plancha, Hamburguesas a la parrilla, vaqueros, entre otros..", resId, null};
			db.addLugar("Comida", lugar);
			
			resId = R.drawable.kfc;
			lugar = new Object[]{"KFC", "Av. La Prensa y Sabanilla , Quito, Ecuador", "(02) 224-5777", "Pollo", resId, null};
			db.addLugar("Comida", lugar);

			resId = R.drawable.mcd;
			lugar = new Object[]{"McDonald's", "Av. Amazonas y NN UU, Quito, Ecuador", "1700 244-622", "Pollo, papas, ensaladas, postres, etc..", resId, null};
			db.addLugar("Comida", lugar);

			resId = R.drawable.perico;
			lugar = new Object[]{"PERICO DE LOS PALOTES", "Shyris e Isla Tortuga, Quito, Ecuador", "-", "Fast Food", resId, null};
			db.addLugar("Comida", lugar);

			resId = R.drawable.wings;
			lugar = new Object[]{"Wings Xpress", "Andalucia y Francisco Salazar , Quito, Ecuador", "09693-5782", "Fast Food", resId, null};
			db.addLugar("Comida", lugar);

          //deporte
          //resId = R.drawable.brau;
          //lugar = new Object[]{"BRAU", "Foch E7-30 y Reina Victoria, Quito - Ecuador", "(02) 604-0134", "En este lugar se vende tal cosa", resId, null};
          //db.addLugar("deporte", lugar);
			/*resId = R.drawable.carolina;
			lugar = new Object[]{"Parque la Carolina", "Av de los Shyris, Quito - Ecuador", "(02) 257-0786", "Aquí, usted puede repozar en los jardines o jugar el fútbol, el baloncesto de práctica y ejercitar su cuerpo con los aeróbicos, montaje al caballo o caminar simplemente al lado de su familia o amigos", resId, null};
			db.addLugar("deporte", lugar);

			resId = R.drawable.metro;
			lugar = new Object[]{"Parque Metropolitano", "Calle Guanguiltagua, Quito - Ecuador", "-", "El parque ofrece un camino de piedra para los amantes del ciclismo de montaña. Usted dispone, además, de los siguientes servicios: Guardianía privada, visitas guiadas, servicio de limpieza, áreas infantiles. Sitios para realizar camping. Canchas de fútbol, volleyball y baloncesto", resId, null};
			db.addLugar("deporte", lugar);

			resId = R.drawable.bombonerita;
			lugar = new Object[]{"La Bombonerita", "De los Rosales 101 y Rio Coca, Quito - Ecuador", "(02) 335-0025", "Bienvenido a La Bombonerita, la mejor cancha de Futbol rapido. Nuestras instalaciones son las mejores de la ciudad y del País. Las ponemos a tu disposición los 365 dias al año las 24 horas al día, llueve, truene o relampagueé", resId, null};
			db.addLugar("deporte", lugar);

			resId = R.drawable.city;
			lugar = new Object[]{"Futbol City", "Bartolome de las Casas Oe1-20 y Av. 10 de Agosto, Quito - Ecuador", "1700 226242", "Canchas de Futbol rapido. Las ponemos a tu disposición los 365 dias al año las 24 horas al día", resId, null};
			db.addLugar("deporte", lugar);
*/
          //karaoke
          //resId = R.drawable.brau;
          //lugar = new Object[]{"BRAU", "Foch E7-30 y Reina Victoria, Quito - Ecuador", "(02) 604-0134", "En este lugar se vende tal cosa", resId, null};
          //db.addLugar("karaoke", lugar);
			resId = R.drawable.akpela;
			lugar = new Object[]{"A k-pela", "Av. Eloy Alfaro y Portugal, Quito, Ecuador", "0984662662", "Parqueadero, Salas Vip, Cocteles, Picadas, Licores y mucho más...", resId, null};
			db.addLugar("Karaoke", lugar);

			resId = R.drawable.elrefugio;
			lugar = new Object[]{"El Refugio Bar Karaoke", "Eloy Alfaro y Portugal, Quito, Ecuador", "0992617568", "Bar, karaoke, picaditas, tablitas, jarras de vino caliente, cócteles, promociones y descuentos.", resId, null};
			db.addLugar("Karaoke", lugar);

			resId = R.drawable.friendscorner;
			lugar = new Object[]{"Friend's Corner", "Amazonas e Isla Floreana, Quito, Ecuador", "(02) 246-7976", "Salas privadas de karaoke, coctelería, alimentos con una extensa carta de platos fuertes y picaditas, seguridad privada, parqueaderos, área para fumadores, zona wifi, y si te cansaste de cantar también se prende la mejor farra con excelente música.", resId, null};
			db.addLugar("Karaoke", lugar);
			
			resId = R.drawable.kanta;
			lugar = new Object[]{"Kanta Pub Karaoke", "Joaquín Pinto y Reina Victoria, Quito, Ecuador", "(02) 254-4370", "Karaoke, salas privadas, micheladas, jirafas, peceras, coctelería, picaditas, combos cerveceros, entre otros...", resId, null};
			db.addLugar("Karaoke", lugar);
			
			resId = R.drawable.karukera;
			lugar = new Object[]{"Karukera Karaoke", "Calama y Reina Victoria, Quito, Ecuador", "(02) 255-6234", "Karaoke, las mejores bebidas como micheladas, mojitos, picaditas, celebramos todo evento social.", resId, null};
			db.addLugar("Karaoke", lugar);
			
			resId = R.drawable.laislakaraoke;
			lugar = new Object[]{"La Isla Karaoke", "Av. General Enríquez y Portoviejo, Quito, Ecuador", "0989211570", "Karaoke, cócteles, cinco variedades de cerveza, licores importados, picaditas, garantía en todos nuestros productos", resId, null};
			db.addLugar("Karaoke", lugar);
			
			resId = R.drawable.latokata;
			lugar = new Object[]{"La Tokata", "Av. Eloy Alfaro , Quito, Ecuador", "0984687772", "Bebidas, cocteles, picaditas, 5 salas privadas de karaoke, presentaciones musicales en vivo, jueves y sábado música bailable en vivo, karaoke en vivo con la banda El Toke y farra con DJ", resId, null};
			db.addLugar("Karaoke", lugar);
			
			resId = R.drawable.latunda;
			lugar = new Object[]{"La Tunda", "Reina Victoria , Quito, Ecuador", "0997925318", "Karaoke, picaditas, comida rápida alitas con diferentes tipos de salsas, bebidas, coctelería, jugos Fussion, micheladas en bota mexicana, seguridad privada, varios ambientes", resId, null};
			db.addLugar("Karaoke", lugar);

          //sano
          //resId = R.drawable.brau;
          //lugar = new Object[]{"BRAU", "Foch E7-30 y Reina Victoria, Quito - Ecuador", "(02) 604-0134", "En este lugar se vende tal cosa", resId, null};
          //db.addLugar("sano", lugar);
			resId = R.drawable.cinemark;
			lugar = new Object[]{"Cinemark", "Av. Republica y NN.UU., Quito, Ecuador", "(02) 226-0302", "Satisfacer las expectativas de nuestros invitados de manera total, brindando calidad audiovisual, comodidad y atención, en un ambiente de seguridad, respeto y cuidado", resId, null};
			db.addLugar("sano", lugar);
			
			resId = R.drawable.multicines;
			lugar = new Object[]{"Multicines S.A", "AV. MARISCAL SUCRE Y AV. JOHN F. KENNEDY, Quito, Ecuador", "1800 352463", "El 100% de sus salas cuenta con equipamiento digital de proyección y sonido de última generación, sistema de butacas numeradas, venta online a través de wwww.multicines.com.ec, Multiticket ubicados en los lobbies de cada cine", resId, null};
			db.addLugar("sano", lugar);
			
			resId = R.drawable.palaciohielo;
			lugar = new Object[]{"PALACIO DEL HIELO", "	Amazonas y Naciones Unidas, Quito, Ecuador", "(02) 226-5073", "La mejor diversión bajo cero en Quito, ubicada en el Centro Comercial Iñaquito. Servicios: patinaje público, cursos de patinaje, cumpleaños y eventos", resId, null};
			db.addLugar("sano", lugar);
			
			resId = R.drawable.supercines;
			lugar = new Object[]{"Supercines", "Av 6 de Diciembre, Quito, Ecuador", "(02) 2240-083", "La cadena de cines más grande y moderna del Ecuador. Con 116 salas a nivel nacional, contamos con los mejores complejos de cine equipados con salas tipo estadio, salas VIP, salas GT-MAX, salas 3D Digital, para que puedas disfrutar de las mejores películas", resId, null};
			db.addLugar("sano", lugar);
// en caso de ser encontrada no se hace nada
      } else {
          /*Log.i("Database", "Encontrada");
          */

      }


  }
}
