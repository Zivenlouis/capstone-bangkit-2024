@extends('layouts.main')


@section('container')

@foreach ($posts as $post) 
    <article class='mb-5 pb-4 border-bottom'>
        <h2>
            <a class='text-decoration-none' href='/posts/{{ $post->slug }}'>{{ $post->title }}</a>
        </h2>
        <h5>By: {{ $post->user->name }} in <a class='text-decoration-none' href='/categories/{{ $post->category->slug }}'>{{ $post->category->name }}</a></h5>
        <p>{{ $post->excerpt }}</p>
        <a class='text-decoration-none' href='/posts/{{ $post->slug }}'>Read more..</a>
    </article>
@endforeach


@endsection