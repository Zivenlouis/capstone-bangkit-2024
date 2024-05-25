<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Collection;

// use Illuminate\Database\Eloquent\Factories\HasFactory;
// use Illuminate\Database\Eloquent\Model;

class Post
{
    // use HasFactory;
    private static $posts = [
        [
            'title' => 'Judul Tulisan Pertama',
            'slug' => 'judul-tulisan-pertama',
            'author' => 'Ziven Louis',
            'body' => "Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga sapiente consequatur beatae minus reiciendis numquam! Id quos sunt tenetur saepe incidunt et sequi quas architecto ad placeat laudantium illum nobis dicta ipsum ex, maxime culpa consectetur, adipisci natus atque magnam laboriosam. Dolores repellendus ipsa repellat praesentium deleniti optio hic dolorem doloremque dicta voluptates facere possimus, laboriosam minima excepturi porro molestiae alias blanditiis accusantium ipsum unde nisi. Eos adipisci accusamus ea ex consectetur, at, libero fugit recusandae nostrum minus alias et."
        ],
        [
            'title' => 'Judul Tulisan Kedua',
            'slug' => 'judul-tulisan-kedua',
            'author' => 'Vellyn Natalie',
            'body' => "Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga sapiente consequatur beatae minus reiciendis numquam! Id quos sunt tenetur saepe incidunt et sequi quas architecto ad placeat laudantium illum nobis dicta ipsum ex, maxime culpa consectetur, adipisci natus atque magnam laboriosam. Dolores repellendus ipsa repellat praesentium deleniti optio hic dolorem doloremque dicta voluptates facere possimus, laboriosam minima excepturi porro molestiae alias blanditiis accusantium ipsum unde nisi. Eos adipisci accusamus ea ex consectetur, at, libero fugit recusandae nostrum minus alias et."
        ],
    ];

    public static function all() {
        return self::$posts; 
    }

    public static function find($slug) {
        $temp = collect(static::all());
        return $temp->firstWhere('slug', $slug);
    }
}
