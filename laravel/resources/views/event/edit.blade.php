@extends('app')

@section('content')

    <div class="col-md-6">
        <h3 class="modal-title">Events</h3>
    </div>
    <div class="col-md-6 text-right">
        <p>
            <a href="{!! url('/event') !!}" class="btn btn-danger">
                <i class="glyphicon glyphicon-backward"></i> Back </a>
        </p>
    </div>

    @include('error_msg')

    {!! Form::model($item->toArray(), array('url' => 'event/'.$item->id, 'class' => 'form', 'files' => true, 'method' => 'put')) !!}

    @include('event._form')
    {!! Form::close() !!}

@endsection