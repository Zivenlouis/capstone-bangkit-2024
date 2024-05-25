<?php

namespace App\Http\Controllers;

use App\Models\Category;
use Illuminate\Http\Request;
use App\Models\Post;

class CategoryController extends Controller
{
    public function index() {
        return view('categories', [
            'title' => 'Categories',
            'categories' => Category::all()
        ]);
    }

    public function show(Category $category) {
        return view('category', [
            'title' => $category->name,
            'posts' => $category -> posts
        ]);
    }
}
