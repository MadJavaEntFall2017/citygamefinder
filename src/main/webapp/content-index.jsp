<%@include file="taglib.jsp"%>
<div class="container">

    <form class="form-horizontal" action="showResults" method="post"  id="city_form">

        <%-- Form Title --%>

        <legend>City Game Finder</legend>

        <br>

        <%-- Zip Code --%>

        <div class="form-group row">
            <label for="zipcode-input" class="col-2 col-form-label">I will be in: </label>
            <div class="col-2">
                <input class="form-control" type="number" name="zipcode-input" id="zipcode-input"
                       minlength="5" maxlength="5" placeholder="Zip Code">
            </div>
        </div>

        <%-- From Date --%>

        <div class="form-group row">
            <label for="from-date-input" class="col-2 col-form-label">From:</label>
            <div class="col-4">
                <input class="form-control" type="date" name="from-date-input" id="from-date-input" >
            </div>
        </div>

        <%-- To Date --%>

        <div class="form-group row">
            <label for="to-date-input" class="col-2 col-form-label">To:</label>
            <div class="col-4">
                <input class="form-control" type="date" name="to-date-input" id="to-date-input" >
            </div>
        </div>

        <%-- Mile Select --%>

        <div class="form-group row">
            <label for="mile-input" class="col-2 col-form-label">Find Games Within (miles):</label>
            <div class="col-2">
                <select class="form-control" name="mile-input" id="mile-input">
                    <option>No Mileage</option>
                    <option>10</option>
                    <option>25</option>
                    <option>50</option>
                    <option>75</option>
                    <option>100</option>
                    <option>250</option>
                </select>
            </div>
        </div>

        <%-- Sport Select --%>

        <div class="form-group row">
            <label for="sport-input" class="col-2 col-form-label">Select Sport:</label>
            <div class="col-2">
                <select class="form-control" name="sport-input" id="sport-input">
                    <option>All</option>
                    <c:forEach items="${sports}" var="sport">
                        <option>${sport}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <%-- Button --%>

        <div class="form-group row">
            <div class="col-4 offset-2">
                <button type="submit" class="btn btn-primary" >Search<span class="glyphicon glyphicon-search"></span></button>
            </div>
        </div>
    </form>

    <script>
        function onSubmit()
        {



            var fromDate=document.getElementById('from-date-input').value;
            var toDate=document.getElementById('to-date-input').value;
            var zip=document.getElementById('zipcode-input').value;
            var mile=document.getElementById('zipcode-input').value;
            
            if (zip.length < 1)
            {
                window.alert("Zip is blank");
                return false;
            }
            
            var fields = $("input[name='zipcode-input']").serializeArray();
            if (fields.length === 0)
            {
                alert('Please select at least one sport.');
                return false;
            }
        }
        $('#city_form').submit(onSubmit)
    </script>

</div>

