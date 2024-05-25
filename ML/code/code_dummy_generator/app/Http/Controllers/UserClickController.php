<?php

namespace App\Http\Controllers;

use App\Models\UserClick;
use App\Http\Requests\StoreUserClickRequest;
use App\Http\Requests\UpdateUserClickRequest;

class UserClickController extends Controller
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
     * @param  \App\Http\Requests\StoreUserClickRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreUserClickRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\UserClick  $userClick
     * @return \Illuminate\Http\Response
     */
    public function show(UserClick $userClick)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\UserClick  $userClick
     * @return \Illuminate\Http\Response
     */
    public function edit(UserClick $userClick)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateUserClickRequest  $request
     * @param  \App\Models\UserClick  $userClick
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateUserClickRequest $request, UserClick $userClick)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\UserClick  $userClick
     * @return \Illuminate\Http\Response
     */
    public function destroy(UserClick $userClick)
    {
        //
    }
}
