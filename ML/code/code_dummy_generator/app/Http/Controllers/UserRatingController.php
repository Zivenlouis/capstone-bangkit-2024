<?php

namespace App\Http\Controllers;

use App\Models\UserRating;
use App\Http\Requests\StoreUserRatingRequest;
use App\Http\Requests\UpdateUserRatingRequest;

class UserRatingController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \App\Http\Requests\StoreUserRatingRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreUserRatingRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\UserRating  $userRating
     * @return \Illuminate\Http\Response
     */
    public function show(UserRating $userRating)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\UserRating  $userRating
     * @return \Illuminate\Http\Response
     */
    public function edit(UserRating $userRating)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateUserRatingRequest  $request
     * @param  \App\Models\UserRating  $userRating
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateUserRatingRequest $request, UserRating $userRating)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\UserRating  $userRating
     * @return \Illuminate\Http\Response
     */
    public function destroy(UserRating $userRating)
    {
        //
    }
}
