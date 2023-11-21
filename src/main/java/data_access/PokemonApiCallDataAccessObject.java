package data_access;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


import org.json.JSONArray;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * The {@code PokemonApiCallDataAccessObject} class provides methods for making HTTP requests to an external API
 * to fetch Pokemon data in the form of a JSONArray.
 */
public class PokemonApiCallDataAccessObject implements PokemonApiCallInterface {

    public JSONArray fetchRawPokemonData(String pokemonName) {
        // Initialize a variable to store the API response data
        JSONArray responseData = new JSONArray();
        try {
            // Make an HTTP request to fetch Pokémon data and store the response
//            responseData = makeHttpRequest(pokemonName);
            responseData = savedMakeHttpRequest(pokemonName);
        } catch (IOException e) {
            // return empty array if error occurs
            return new JSONArray();
        }

        // Check if the response data is empty, indicating that the Pokemon was not found
        if (responseData == null || responseData.isEmpty()) {
            // Print a message indicating that the requested Pokemon does not exist
            System.out.println("[API DATA EMPTY] Pokemon with name '" + pokemonName + "' not found.");
            responseData = new JSONArray();
        }

        // Return the fetched Pokémon data as a JSONArray (may be null if not found)
        return responseData;
    }

    /**
     * Makes an HTTP GET request to an external API to fetch Pokemon data.
     *
     * @param pokemonName The name of the Pokemon to fetch.
     * @return A JSONArray containing the response data from the API.
     * @throws IOException If an IO error occurs during the HTTP request.
     */
    public static JSONArray makeHttpRequest(String pokemonName) throws IOException {
        // Construct the URL with the provided Pokémon name
        String apiUrl = "https://ex.traction.one/pokedex/pokemon/" + pokemonName;

        // Create a URL object representing the API endpoint
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        int responseCode = -1;
        while (responseCode != HttpURLConnection.HTTP_OK) {
            responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Received non-OK response code: " + responseCode);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
            }
        }

        // Initialize a JSONArray to store the response data
        JSONArray responseData = null;

        // Check if the response code indicates a successful request (e.g., 200 OK)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Create a BufferedReader to read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            // Read the response line by line and append it to the response StringBuilder
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Close the reader
            reader.close();

            // Close the connection
            connection.disconnect();

            // Parse the response data as a JSONArray
            responseData = new JSONArray(response.toString());
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            // Close the connection
            connection.disconnect();

            // Print a message indicating that the requested Pokemon does not exist
            System.out.println("[API ISSUE] Pokemon with name '" + pokemonName + "' not found.");
        } else {
            // Close the connection
            connection.disconnect();

            throw new IOException("HTTP request failed with response code: " + responseCode);
        }

        // Return the response data as a JSONArray (may be empty if not found)
        return responseData;
    }

    public static JSONArray savedMakeHttpRequest(String getPokemonNumber) throws IOException {
        Map<String, String> loadedPokemonData = loadPokemonDataFromFile("pokemon_data.pickle");

        for (Map.Entry<String, String> entry : loadedPokemonData.entrySet()) {
            String pokemonNumber = entry.getKey();
            String responseDataString = entry.getValue();

            JSONArray responseData = new JSONArray(responseDataString);

//            System.out.println("Pokemon Number: " + pokemonNumber);
//            System.out.println("Response Data: " + responseData);

            if (pokemonNumber.equals(getPokemonNumber)) {
                return responseData;
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        int[] pokemonNumbers = { 1, 10, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 11, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 12, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 13, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 14, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 15, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 16, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 17, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 18, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 19, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 2, 20, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 21, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 22, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 23, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 24, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 25, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 26, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 27, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 28, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 29, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 3, 30, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 31, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 32, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 33, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 34, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 35, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 36, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 37, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 38, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 39, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 4, 40, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 41, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 42, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 43, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 44, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 45, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 46, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 47, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 48, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 49, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 5, 50, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 51, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 52, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 53, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 54, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 55, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 56, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 57, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 58, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 59, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 6, 60, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 61, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 62, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 63, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 64, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 65, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 66, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 67, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 68, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 69, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 7, 70, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 71, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 72, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 73, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 74, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 75, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 76, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 77, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 78, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 79, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 8, 80, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 81, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 82, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 83, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 84, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 85, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 86, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 87, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 88, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 89, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 9, 90, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 91, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 92, 920, 93, 94, 95, 953, 954, 955, 956, 96, 960, 961, 963, 964, 97, 971, 972, 976, 977, 98, 981, 982, 99 };
//
////        int[] pokemonNumbers = {1, 10, 100, 101};
//
//        Map<String, String> pokemonData = new HashMap<>();
//
//        for (int pokemonNumber : pokemonNumbers) {
//            try {
//                String pokemonNumberStr = String.valueOf(pokemonNumber);
//
//                JSONArray responseData = makeHttpRequest(pokemonNumberStr);
//
//                // Convert JSONArray to String before saving
//                String responseDataString = responseData.toString();
//
//                pokemonData.put(pokemonNumberStr, responseDataString);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        savePokemonDataToFile(pokemonData, "pokemon_data.pickle");
//    }
    private static void savePokemonDataToFile(Map<String, String> pokemonData, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pokemonData);
            System.out.println("Pokemon data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        // Load Pokemon data from file
//        Map<String, String> loadedPokemonData = loadPokemonDataFromFile("pokemon_data.pickle");
//
//        for (Map.Entry<String, String> entry : loadedPokemonData.entrySet()) {
//            String pokemonNumber = entry.getKey();
//            String responseDataString = entry.getValue();
//
//            JSONArray responseData = new JSONArray(responseDataString);
//
//            System.out.println("Pokemon Number: " + pokemonNumber);
//            System.out.println("Response Data: " + responseData);
//        }
//    }

    private static Map<String, String> loadPokemonDataFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Read the object from the file
            Object loadedObject = ois.readObject();

            if (loadedObject instanceof Map) {
                return (Map<String, String>) loadedObject;
            } else {
                System.err.println("Unexpected type of object in the pickle file.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Return an empty map in case of an error
        return new HashMap<>();
    }


}

