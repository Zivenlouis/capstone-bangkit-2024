<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('phones', function (Blueprint $table) {
            $table->id();
            $table->string('brand');
            $table->string('name');
            $table->string('image');
            $table->string('release_date');
            $table->string('resolution');
            $table->string('weight');
            $table->string('os');
            $table->string('chipset');
            $table->string('memory');
            $table->string('ram');
            $table->string('main_camera_1');
            $table->string('main_camera_2');
            $table->string('main_camera_3');
            $table->string('selfie_camera');
            $table->string('earphone_jack');
            $table->string('battery');
            $table->string('charging');
            $table->string('colors');
            $table->string('nfc');
            $table->string('price');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('phones');
    }
};
