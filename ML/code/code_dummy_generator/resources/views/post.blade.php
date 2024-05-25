@extends('layouts.main')

@section('container')
<article class='mb-5'>
    <h2>
        <a class='text-decoration-none' href='/posts/{{ $posts->slug }}'>{{ $posts->title }}</a>
    </h2>
    <h5>By: <a href='/users/{{ $posts->user->id }}'>{{ $posts->user->name }}</a> in <a href='/categories/{{ $posts->category->slug }}'>{{ $posts->category->name }}</a></h5>
    {!! $posts ->body !!}
</article>

<a href='/posts'>
    Back to Post
</a>
@endsection


