@extends('app')


@section('content')

    <div class="col-md-6">
        <h3 class="modal-title">Pages</h3>
    </div>
    <div class="col-md-6 text-right">
        <p>
            <a href="{!! url('/page') !!}" class="btn btn-danger">
                <i class="glyphicon glyphicon-backward"></i> Back </a>
        </p>
    </div>

    @include('error_msg')

    {!! Form::model($item->toArray(), array('url' => 'page/'.$item->id, 'class' => 'form', 'method' => 'put')) !!}

    @include('page._form')
    {!! Form::close() !!}

@endsection