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

    {!! Form::open(array('url' => 'event', 'class' => 'form', 'files' => true)) !!}
    @include('event._form')
    {!! Form::close() !!}

@endsection