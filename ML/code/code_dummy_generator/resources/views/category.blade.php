@extends('layouts.main')

@section('container')
<h1 class='mb-5'>Ini {{ $title }}</h1>

@foreach ($posts as $post) 
    <article class='mb-5 pb-4 border-bottom'>
        <h2>
            <a class='text-decoration-none' href='/posts/{{ $post->slug }}'>{{ $post->title }}</a>
        </h2>
        <h5>By: {{ $post->user->name }}</h5>
        <p>{{ $post->excerpt }}</p>
        <a class='text-decoration-none' href='/posts/{{ $post->slug }}'>Read more..</a>
    </article>

@endforeach

<a href='/categories'>Back to Category</a>

@endsection