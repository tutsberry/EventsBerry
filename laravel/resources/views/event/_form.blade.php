<div class="col-md-6">
    <!-- Code Form Input -->
    <div class="form-group">
        {!! Form::label('code', 'Code:') !!}
        {!! Form::text('code', null, ['class' => 'form-control']) !!}
    </div>

    <!-- Name Form Input -->
    <div class="form-group">
        {!! Form::label('name', 'Name:') !!}
        {!! Form::text('name', null, ['class' => 'form-control']) !!}
    </div>

    <!-- Description Form Input -->
    <div class="form-group">
        {!! Form::label('description', 'Description:') !!}
        {!! Form::text('description', null, ['class' => 'form-control']) !!}
    </div>

    <div class="row">
        <div class="col-md-6">
            <!-- Starts Form Input -->
            <div class="form-group">
                {!! Form::label('starts', 'Starts:') !!}
                {!! Form::input('date', 'starts', null, ['class' => 'form-control', 'placeholder' => 'as YYYY-MM-DD']) !!}
            </div>
        </div>

        <div class="col-md-6">
            <!-- Ends Form Input -->
            <div class="form-group">
                {!! Form::label('ends', 'Ends:') !!}
                {!! Form::input('date', 'ends', null, ['class' => 'form-control', 'placeholder' => 'as YYYY-MM-DD']) !!}
            </div>
        </div>
    </div>

    <!-- Venue Form Input -->
    <div class="form-group">
        {!! Form::label('venue', 'Venue:') !!}
        {!! Form::text('venue', null, ['class' => 'form-control']) !!}
    </div>

    <!-- Online Form Input -->
    <div class="form-group">
        {!! Form::label('online', 'Online:') !!}
        {!! Form::checkbox('online', true, ['class' => 'form-control']) !!}
    </div>

    <div class="row">
        <div class="col-sm-6">
            <!-- Submit Form Button -->
            {!! Form::submit('Save', ['class' => 'btn btn-primary btn-block btn-lg']) !!}
        </div>
    </div>
</div>

<div class="col-md-6">
    <fieldset>
        <legend>Logo &amp; Splash Screen</legend>
        @if(!empty($item['param']['logo']) )
            <a href="{!! $item['param']['logo'] !!}" target="_blank">
                <img src="{!! $item['param']['logo'] !!}" class="pull-right" height="60">
            </a>
        @endif
        <!-- Logo Form Input -->
        <div class="form-group">
            {!! Form::label('param[logo]', 'Logo:') !!}
            {!! Form::file('param[logo]', null, ['class' => 'form-control']) !!}
            <p class="help-block">Logo must be PNG and width should be 300px</p>
        </div>

        @if(!empty($item['param']['splash_screen']) )
             <a href="{!! $item['param']['splash_screen'] !!}" target="_blank">
                 <img src="{!! $item['param']['splash_screen'] !!}" class="pull-right" height="60">
             </a>
        @endif
        <!-- Logo Form Input -->
        <div class="form-group">
            {!! Form::label('param[splash_screen]', 'Splash Screen:') !!}
            {!! Form::file('param[splash_screen]', null, ['class' => 'form-control']) !!}
            <p class="help-block">Logo must be jpg and size should be 480x800px</p>
        </div>
    </fieldset>

    <fieldset>
        <legend>Colors</legend>
        <div class="row">
            <div class="col-md-3">
                {!! Form::label('param[bg_color]', 'Bg Color:') !!}
                {!! Form::input('color', 'param[bg_color]', null, ['class' => 'form-control']) !!}
            </div>
            <div class="col-md-3">
                <!-- Param[color_primary] Form Input -->
                {!! Form::label('param[color_primary]', 'Primary:') !!}
                {!! Form::input('color', 'param[color_primary]', null, ['class' => 'form-control']) !!}
            </div>
            <div class="col-md-3">
                {!! Form::label('param[color_primary_dark]', 'Primary Dark:') !!}
                {!! Form::input('color', 'param[color_primary_dark]', null, ['class' => 'form-control']) !!}
            </div>
            <div class="col-md-3">
                {!! Form::label('param[color_primary_light]', 'Primary Light:') !!}
                {!! Form::input('color', 'param[color_primary_light]', null, ['class' => 'form-control']) !!}
            </div>
        </div>

        <div class="row">
            <div class="col-md-3">
                {!! Form::label('param[color_primary_accent]', 'Accent:') !!}
                {!! Form::input('color', 'param[color_primary_accent]', null, ['class' => 'form-control']) !!}
            </div>

            <div class="col-md-3">
                {!! Form::label('param[text_primary]', 'Text Primary:') !!}
                {!! Form::input('color', 'param[text_primary]', null, ['class' => 'form-control']) !!}
            </div>
            <div class="col-md-3">
                {!! Form::label('param[text_secondary]', 'Text Secondary:') !!}
                {!! Form::input('color', 'param[text_secondary]', null, ['class' => 'form-control']) !!}
            </div>
            <div class="col-md-3">
                {!! Form::label('param[text_gray]', 'Text Gray:') !!}
                {!! Form::input('color', 'param[text_gray]', null, ['class' => 'form-control']) !!}
            </div>

        </div>
    </fieldset>
</div>

<div class="clearfix"></div>