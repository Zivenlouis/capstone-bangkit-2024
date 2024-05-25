<?php

namespace App\Http\Controllers;

use App\Models\Tes;
use App\Http\Requests\StoreTesRequest;
use App\Http\Requests\UpdateTesRequest;

class TesController extends Controller
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
     * @param  \App\Http\Requests\StoreTesRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreTesRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Tes  $tes
     * @return \Illuminate\Http\Response
     */
    public function show(Tes $tes)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Tes  $tes
     * @return \Illuminate\Http\Response
     */
    public function edit(Tes $tes)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateTesRequest  $request
     * @param  \App\Models\Tes  $tes
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateTesRequest $request, Tes $tes)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Tes  $tes
     * @return \Illuminate\Http\Response
     */
    public function destroy(Tes $tes)
    {
        //
    }
}
