<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use League\Csv\Writer;
use Illuminate\Support\Facades\Storage;
use App\Models\User;
use App\Models\Phone;
use App\Models\UserRating;
use App\Models\UserSurvey;
use App\Models\UserClick;
use Carbon\Carbon;
class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {

        //Masukkan dataset hp ke dalam phone database
        $datas = file(base_path('resources/data/phone_dataset.csv'));
        $header = str_getcsv(array_shift($datas));
        foreach($datas as $row) {
            $phone = [];
            $row = str_getcsv($row);
            foreach($row as $index => $item) {
                $phone[$header[$index]] = $item;
            }
            Phone::create($phone);
        }


        //Buat rating user yang spesifik ke brand tertentu
        $brands = [
            'Oppo', 'Asus', 'Infinix',
            'Samsung', 'Vivo', 'Huawei',
            'Apple', 'Realme', 'Xiaomi',
            'Poco'
        ];
        $USER_PER_BRAND = 50;
        $RATING_PER_USER = 10;
        $RANDOM_LIKE_BRAND_RATIO = True;
        $LIKE_BRAND_RATIO = 0.7;
        foreach($brands as $brand) {
            for($i = 0; $i < $USER_PER_BRAND; $i++) {
                $user = User::create([
                    'name' => $brand . ' lover ' . $i,
                    'email' =>  $brand . 'lover' . $i . '@gmail.com',
                    'password' => bcrypt('12345')
                ]);
                
                if($RANDOM_LIKE_BRAND_RATIO) $LIKE_BRAND_RATIO = 0.1 * mt_rand(5, 10);

                $BRAND_COUNT = intval($RATING_PER_USER * $LIKE_BRAND_RATIO);
                $brand_phones = Phone::where('brand', $brand)->inRandomOrder()->limit($BRAND_COUNT)->get();
                $OTHER_BRAND_COUNT = $RATING_PER_USER - $brand_phones->count();
                $other_brand_phones = Phone::whereNot('brand', $brand)->inRandomOrder()->limit($OTHER_BRAND_COUNT)->get();
                
                foreach($brand_phones as $phone) {
                    UserRating::create([
                        'user_id' => $user->id,
                        'phone_id' => $phone->id,
                        'rating' => mt_rand(4,5)
                    ]);
                    UserClick::create([
                        'user_id' => $user->id,
                        'phone_id' => $phone->id,
                        'visit_time' => Carbon::now()
                    ]);
                }
                foreach($other_brand_phones as $phone) {
                    UserRating::create([
                        'user_id' => $user->id,
                        'phone_id' => $phone->id,
                        'rating' => mt_rand(1,5)
                    ]);
                    UserClick::create([
                        'user_id' => $user->id,
                        'phone_id' => $phone->id,
                        'visit_time' => Carbon::now()
                    ]);
                }
            }
        }

        //Buat data survey dummy. Hp yang dirating dan berhubungan hanya berfokus pada Q5, Q6 dan Q8
        // Q1 = Seberapa penting performa? 1-5
        // Q2 = Seberapa penting kamera? 1-5
        // Q3 = Seberapa penting baterai? 1-5
        // Q4 = Seberapa penting software? 1-5
        // Q5 = Seberapa penting RAM? 1-5
        // Q6 = Seberapa penting penyimpanan? 1-5
        // Q7 = Apakah mempertimbangkan brand hp? True/False
        // Q8 = Brand favorit? Apple, Samsung, Asus, Oppo, Vivo, Realme, Xiaomi, Poco, Infinix, lainnya/tidak ada
        $SURVEYED_USER = 1000;
        $RATED_PHONE_AFTER_SURVEY = 5;
        $STAR_RANGE = [
            'ram' => [
                1 => 4,
                2 => 6,
                3 => 8,
                4 => 12,
                5 => 16
            ],
            'memory' => [
                1 => 128,
                2 => 256,
                3 => 512,
                4 => 1024,
                5 => 1024
            ]
        ];
        for($i = 0; $i < $SURVEYED_USER; $i++) {
            $user = User::create([
                'name' => 'Surveyed user ' . $i,
                'email' => 'surveyedUser' . $i . '@gmail.com',
                'password' => bcrypt('12345'),
                'isSurveyed' => 'TRUE'
            ]);
            $survey = UserSurvey::create([
                'user_id' => $user->id,
                'q1' => mt_rand(1,5),
                'q2' => mt_rand(1,5),
                'q3' => mt_rand(1,5),
                'q4' => mt_rand(1,5),
                'q5' => mt_rand(1,5),
                'q6' => mt_rand(1,5),
                'q7' => 'TRUE',
                'q8' => array_rand($brands),
            ]);
            //Mencari hp terdekat berdasarkan hasil survey pada Q5, Q6 dan Q8 (prioritas Q5, Q6, Q8)
            $ram_rating = $survey->q5;
            $memory_rating = $survey->q6;
            $brand_rating = $survey->q8;
            while(true) {
                $phones = Phone::where('memory', '>', $STAR_RANGE['memory'][$memory_rating])
                ->where('ram', '>', $STAR_RANGE['ram'][$ram_rating])
                ->get();

                if($brand_rating != Null) {
                    $phones = $phones->where('brand', $brand_rating);
                }

                if ($phones->count() >= $RATED_PHONE_AFTER_SURVEY) {
                    $phones = $phones->sortByDesc('ram')->take($RATED_PHONE_AFTER_SURVEY);
                    break;
                } elseif($brand_rating != Null) {
                    $brand_rating = Null;
                } elseif($ram_rating > 1 && $memory_rating > 1) {
                    if($ram_rating > $memory_rating) {
                        $ram_rating--;
                    } else {
                        $memory_rating--;
                    }
                } else {
                    $phones = Phone::orderBy('ram', 'desc')->take($RATED_PHONE_AFTER_SURVEY)->get();
                    break;
                }

            }

            foreach($phones as $phone) {
                UserRating::create([
                    'user_id' => $user->id,
                    'phone_id' => $phone->id,
                    'rating' => mt_rand(4,5)
                ]);
                UserClick::create([
                    'user_id' => $user->id,
                    'phone_id' => $phone->id,
                    'visit_time' => Carbon::now()
                ]);
            }

        }



        //Random user click
        $PHONE_CLICK_PER_USER = 20;
        $RANDOM_PHONE_CLICK_PER_USER = True;
        $MIN_PHONE_CLICK_PER_USER = 10;
        $MAX_PHONE_CLICK_PER_USER = 20;
        $users = User::all();
        foreach($users as $user) {
            if ($RANDOM_PHONE_CLICK_PER_USER) $PHONE_CLICK_PER_USER = mt_rand($MIN_PHONE_CLICK_PER_USER, $MAX_PHONE_CLICK_PER_USER);
            $phones = Phone::inRandomOrder()->limit($PHONE_CLICK_PER_USER)->get();
            foreach($phones as $phone) {
                $clicked = UserClick::where('user_id', $user->id)->where('phone_id', $phone->id)->first();
                if (!$clicked) {
                    UserClick::create([
                        'user_id' => $user->id,
                        'phone_id' => $phone->id,
                        'visit_time' => Carbon::now()
                    ]);
                }
            }
        }

        $this->downloadCSV();
    }

    public function downloadCSV() {
        $users = User::all();
        $users_col = ['id', 'name', 'email', 'isSurveyed', 'password'];
        $user_ratings = UserRating::all();
        $user_ratings_col = ['user_id', 'phone_id', 'rating'];
        $user_clicks = UserClick::all();
        $user_clicks_col = ['user_id', 'phone_id', 'visit_time'];
        $user_surveys = UserSurvey::all();
        $user_surveys_col = ['user_id', 'q1', 'q2', 'q3', 'q4', 'q5', 'q6', 'q7', 'q8'];
        $this->generateCSV($users, $users_col, 'users.csv');
        $this->generateCSV($user_ratings, $user_ratings_col, 'user_ratings.csv');
        $this->generateCSV($user_clicks, $user_clicks_col, 'user_clicks.csv');
        $this->generateCSV($user_surveys, $user_surveys_col, 'user_surveys.csv');
    }

    public function generateCSV($data, $col, $filename) {
        $writer = Writer::createFromString('');
        $writer->insertOne($col);
        foreach($data as $row) {
            $temp = [];
            foreach($col as $name) {
                $temp[$name] = $row[$name];
            }
            $writer->insertOne($temp);
        }
        $filePath = $filename;
        Storage::disk('local')->put($filePath, $writer->getContent());
    }
}
