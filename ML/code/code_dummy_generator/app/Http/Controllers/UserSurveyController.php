<?php

namespace App\Http\Controllers;

use App\Models\UserSurvey;
use App\Http\Requests\StoreUserSurveyRequest;
use App\Http\Requests\UpdateUserSurveyRequest;

class UserSurveyController extends Controller
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
     * @param  \App\Http\Requests\StoreUserSurveyRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreUserSurveyRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\UserSurvey  $userSurvey
     * @return \Illuminate\Http\Response
     */
    public function show(UserSurvey $userSurvey)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\UserSurvey  $userSurvey
     * @return \Illuminate\Http\Response
     */
    public function edit(UserSurvey $userSurvey)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateUserSurveyRequest  $request
     * @param  \App\Models\UserSurvey  $userSurvey
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateUserSurveyRequest $request, UserSurvey $userSurvey)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\UserSurvey  $userSurvey
     * @return \Illuminate\Http\Response
     */
    public function destroy(UserSurvey $userSurvey)
    {
        //
    }
}
