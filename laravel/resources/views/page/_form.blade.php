@section('css')
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
@endsection

<div class="col-md-8 col-md-offset-2">

    <!-- Event_id Form Input -->
    <div class="form-group">
        {!! Form::label('event_id', 'For Event:') !!}
        {!! Form::select('event_id', $event_list, null, ['class' => 'form-control']) !!}
    </div>


    <!-- Title Form Input -->
    <div class="form-group">
        {!! Form::label('title', 'Title:') !!}
        {!! Form::text('title', null, ['class' => 'form-control']) !!}
    </div>

    <div class="row">
        <div class="col-sm-4">
            <!-- Icon Form Input -->
            <div class="form-group">
                {!! Form::label('icon', 'Icon Unicode:') !!}
                {!! Form::text('icon', null, ['class' => 'form-control col-sm-6']) !!}
                <p class="help-block">Get Font Awesome unicode <a href="http://fortawesome.github.io/Font-Awesome/cheatsheet/" target="_blank"> from here</a></p>
            </div>
        </div>

        <div class="col-sm-6">
            <p id="faPreview" class="fa text-warning ">&#xf118;</p>
        </div>
    </div>

    <!-- Body Form Input -->
    <div class="form-group">
        {!! Form::label('body', 'Body:') !!}
        {!! Form::textarea('body', null, ['class' => 'form-control']) !!}
    </div>

    <!-- Online Form Input -->
    <div class="form-group">
        {!! Form::label('active', 'Active:') !!}
        {!! Form::checkbox('active', true, ['class' => 'form-control']) !!}
    </div>


    <div class="row">
        <div class="col-sm-6">
            <!-- Submit Form Button -->
            {!! Form::submit('Save', ['class' => 'btn btn-primary btn-block btn-lg']) !!}
        </div>
        <div class="co-md-6">
        <a href="{!! url('/page') !!}" class="btn btn-danger btn-lg">
                     <i class="glyphicon glyphicon-backward"></i> Back </a>
        </div>
    </div>
</div>

<div class="clearfix"></div>

@section('js')
    <script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#icon').on('change', function(e){
                $('#faPreview').html($(this).val());
            });
        });

        CKEDITOR.replace( 'body', {
            height:600,
            uiColor:'#FFFFFF'
        } );
    </script>
@endsection